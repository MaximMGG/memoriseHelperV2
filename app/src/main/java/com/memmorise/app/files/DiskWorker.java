package com.memmorise.app.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;

public class DiskWorker {

    private User user;
    private Library library;

    private boolean userConfig = false;


    public void setup(User user) throws IOException {
        this.user = user;
            checkExistingUserConfig();
            checkExistingUserLibraryDir();
            checkExistingCurrentUserLibraryDir();
    }


    private void checkExistingCurrentUserLibraryDir() throws IOException {
        if (Files.exists(Path.of(Pathes.PATH_TO_RESOURCES + user.getUsername() + "Libraries/"))){
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
            this.userConfig = true;
        } else {
            Files.createDirectory(Path.of("resources/"));
            Files.createFile(Path.of(Pathes.PATH_TO_USER_CONFIG));
            wrightUserInfo();
        }
    }

    private void wrightUserInfo() throws IOException {
        String userInfo = "user:%s;userLibraries:".formatted(user.getUsername());
        Files.writeString(Path.of(Pathes.PATH_TO_USER_CONFIG), userInfo);
    }


    public static void main(String[] args) {
       try {
        new DiskWorker().setup(new User("TestUser"));
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
