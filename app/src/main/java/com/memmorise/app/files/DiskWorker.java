package com.memmorise.app.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.memmorise.app.exception.MemoriseException;
import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;

public class DiskWorker {

    private User user;
    private Library library;


    public void setup(User user) throws MemoriseException {
        this.user = user;
        try {
            checkExistingUserConfig();
            checkExistingUserLibraryDir();
        } catch (IOException e) {
            throw new MemoriseException("can not create file in 'resources/userconfig.txt");
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
            Files.createFile(Path.of(Pathes.PATH_TO_USER_CONFIG));
        }
    }

}
