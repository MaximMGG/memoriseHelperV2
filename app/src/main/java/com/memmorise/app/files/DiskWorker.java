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
    private Library library;

    public void setup(User user) throws IOException {
        this.user = user;
            checkExistingUserConfig();
            checkExistingUserLibraryDir();
            checkExistingCurrentUserLibraryDir();
            if (isNewUser()) {
                wrightUserInfo();
            } else {
                setUserLibraries();
            }
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
    }

    public void setLibrary(Library library) throws IOException {
        this.library = library;
        createLibrary();
    }

    private void createLibrary() throws IOException {
        Files.createFile(Path.of(Pathes.PATH_TO_USER_LIBRARIES + user.getUsername() + "Libraries/" +
                library.getLibraryName() + ".txt"));
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
            wrightUserInfo();
        }
    }

    private void wrightUserInfo() throws IOException {
        String userInfo = "user:%s;userLibraries:".formatted(user.getUsername());
        Files.writeString(Path.of(Pathes.PATH_TO_USER_CONFIG), userInfo, StandardOpenOption.APPEND);
    }


    public static void main(String[] args) {
       try {
        new DiskWorker().setup(new User("TestUser"));
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
