package com.memmorise.app.files;

import org.junit.jupiter.api.Test;

import com.memmorise.app.user.User;

public class DiskWorkerTest {
    
    @Test
    void setupTest() {
        User user = new User("TestUser");
        try {
            new DiskWorker().setup(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
