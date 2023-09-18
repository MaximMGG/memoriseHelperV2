package com.memmorise.app.files;

import org.junit.jupiter.api.Test;

import com.memmorise.app.user.User;

public class DiskWorkerTest {
    
    @Test
    void setupTest() {
        User user = User.getInstance();
        try {
            new DiskWorker().setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
