package com.memmorise.app.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserInfoTest {
    
    @Test
    public void setUserInfoTest() {
        List<String> userInfoFromDisk = List.of("user:Maxim;libraries:MyfirstGiperLibrary,Cats,Dogs", "user:Polya;libraries:",
                                                            "user:John;libraries:One,Two");
        Map<String, List<String>> expected = new HashMap<>();
        expected.put("Maxim", List.of("MyfirstGiperLibrary", "Cats", "Dogs"));
        expected.put("Polya", null);
        expected.put("John", List.of("One", "Two"));

        UserInfo ui = UserInfo.getInstancse();
        ui.setUserConfig(userInfoFromDisk);

        Assertions.assertThat(ui.getUserConfig()).isEqualTo(expected);
    }


    @Test
    public void getUserInfoForDiskWriteTest() {
        List<String> userInfoFromDisk = List.of("user:Maxim;libraries:MyfirstGiperLibrary,Cats,Dogs", "user:Polya;libraries:",
                                                            "user:John;libraries:One,Two");
        UserInfo ui = UserInfo.getInstancse();
        ui.setUserConfig(userInfoFromDisk);
        List<String> result = ui.getUserInfoForDiskWrite();
        
        for (String s : result) {
            assertTrue(userInfoFromDisk.contains(s));
        }
    }

    @Test
    public void addLibraryInUserInfoTest() {
        List<String> userInfoFromDisk = List.of("user:Maxim;libraries:MyfirstGiperLibrary,Cats,Dogs", "user:Polya;libraries:",
                                                            "user:John;libraries:One,Two");
        Map<String, List<String>> expected = new HashMap<>();
        expected.put("Maxim", List.of("MyfirstGiperLibrary", "Cats", "Dogs"));
        expected.put("Polya", List.of("Mamy"));
        expected.put("John", List.of("One", "Two", "Three"));

        UserInfo ui = UserInfo.getInstancse();
        ui.setUserConfig(userInfoFromDisk);
        ui.addLibraryInUserInfo("Polya", "(en-ru)", "Mamy");
        ui.addLibraryInUserInfo("John", "(en-ru)", "Three");

        Assertions.assertThat(ui.getUserConfig()).isEqualTo(expected);

    }
}
