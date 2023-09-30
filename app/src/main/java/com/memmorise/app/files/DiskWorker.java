package com.memmorise.app.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;
import com.memmorise.app.user.UserInfo;
import com.memmorise.app.utils.LangUtils;

public class DiskWorker {

    private User user;
    private UserInfo userInfo;

    public DiskWorker() {
        this.user = User.getInstance();
        userInfo = UserInfo.getInstancse();
    }

    public void setup() throws IOException {
            checkExistingUserConfig();
            checkExistingUserLibraryDir();
            checkExistingCurrentUserLibraryDir();
            if (isNewUser()) {
                wrightUserInfo();
            } else {
                setUserLibraries();
            }
        userInfo.setUserConfig(getUserConfigFromDidk());
    }

    
    public void saveLibraryOnDisk(Library lib, boolean changed) throws IOException {
        Files.write(lib.getPathToLibrary(), lib.getLibraryContentByList());
        if (!changed)
            writeLibraryInUserConfig(lib);
    }

    public Library getLibraryFromDisk(Library lib) throws IOException {
        if (lib == null) {
            return lib;
        }

        lib.setLibraryContent(
            Files.readAllLines(lib.getPathToLibrary())
        );

        return lib;
    }

    private void setUserLibraries() throws IOException {
        List<String> userInfo = Files.readAllLines(Path.of(Pathes.PATH_TO_USER_CONFIG));
        List<Library> userLibraries = new ArrayList<>();
        
        for (String line : userInfo) {
            line = line.replace("user:", "");
            line = line.replace(";libraries:", ",");
            String[] buffer = line.split(",");
            if (buffer[0].equals(user.getUsername()) && buffer.length > 1) {
                for (int i = 1; i < buffer.length; i++) {
                    String fromTo = buffer[i].substring(1, 6);
                    Library l = new Library(buffer[i].replaceAll("\\(.+\\)", ""));
                    l.setTranslator(LangUtils.getTranlator(fromTo));
                    userLibraries.add(l);
                }
            }
        }
        user.setLibraries(userLibraries);
    }

    private boolean isNewUser() throws IOException {

        List<String> userInfo = Files.readAllLines(Path.of(Pathes.PATH_TO_USER_CONFIG));
        String usersName = "user:" + user.getUsername();
        for (String line : userInfo) {
            if (line.split(";")[0].equals(usersName)) {
                return false;
            }
        }
        return true;
    }


    private void checkExistingCurrentUserLibraryDir() throws IOException {
        if (Files.exists(Path.of(Pathes.PATH_TO_USER_LIBRARIES + user.getUsername() + "Libraries/"))){
        } else {
            Files.createDirectories(Path.of(Pathes.PATH_TO_USER_LIBRARIES + user.getUsername() + "Libraries/"));
        }
    }

    
    private void checkExistingUserLibraryDir() throws IOException {
        if (Files.exists(Path.of(Pathes.PATH_TO_USER_LIBRARIES))) {
        } else {
            Files.createDirectories(Path.of(Pathes.PATH_TO_USER_LIBRARIES));
        }
    }

    
    private void checkExistingUserConfig() throws IOException {
        if (Files.exists(Path.of(Pathes.PATH_TO_USER_CONFIG))) {
        } else {
            Files.createDirectory(Path.of("resources/"));
            Files.createFile(Path.of(Pathes.PATH_TO_USER_CONFIG));
        }
    }

    private void wrightUserInfo() throws IOException {
        String userInfo = "user:%s;libraries:\n".formatted(user.getUsername());
        Files.writeString(Path.of(Pathes.PATH_TO_USER_CONFIG), userInfo, StandardOpenOption.APPEND);
    }

    private List<String> getUserConfigFromDidk() throws IOException {
        return Files.readAllLines(Path.of(Pathes.PATH_TO_USER_CONFIG));
    }


    private void writeLibraryInUserConfig(Library library) throws IOException {
        userInfo.addLibraryInUserInfo(user.getUsername(), library.getTranslator().getFromTo(), library.getLibraryName());
        Files.write(Path.of(Pathes.PATH_TO_USER_CONFIG), userInfo.getUserInfoForDiskWrite());
    }
}
