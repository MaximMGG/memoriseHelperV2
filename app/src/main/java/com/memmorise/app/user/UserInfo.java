package com.memmorise.app.user;

import java.util.List;
import java.util.Map;

public class UserInfo {

    private Map<String, List<String>> userInfo;

    public void setUserInfo(List<String> userInfoFromDisk) {
        for (String line : userInfoFromDisk) {
            String[] buffer = line.split(";");
            String key = buffer[0].split(":")[0];
            String[] libs = buffer[1].split(":");
            if (libs[1] == null) {}
            else {
                userInfo.put(key, List.of(libs[1].split(",")));
           }
        }
    }
}
