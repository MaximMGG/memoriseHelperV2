package com.memmorise.app.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;

public class DiskWorker {

    private User user;

    public DiskWorker() {
        this.user = User.getInstance();
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
    }

    public void saveLibraryOnDisk(Library lib) {
        lib.getLibraryContentByList().stream()
                            .forEach(string -> {
                                try {
                                    Files.writeString(lib.getPathToLibrary(), string);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
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
        String usersName = "user:" + user.getUsername();
        String[] userLib;
        List<Library> userLibraries = new ArrayList<>();

        for(String line : userInfo) {
            String[] lines = line.split(";");
            if (lines[0].equals(usersName)) {
                userLib = lines[1].split(",");
                for (int i = 0; i < userLib.length; i++) {
                    if (i == 0) {
                        userLibraries.add(new Library(userLib[i].split(":")[1]));
                    } else {
                        userLibraries.add(new Library(userLib[i]));
                    }
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


    private void writeLibraryInUserInfo() {


    }

}
