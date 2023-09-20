package com.memmorise.app.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfo {

    private Map<String, List<String>> userInfo;

    public void setUserInfo(List<String> userInfoFromDisk) {
        userInfo = new HashMap<>();
        for (String line : userInfoFromDisk) {
            String[] buffer = line.split(";");
            String key = buffer[0].split(":")[1];
            String[] libs = buffer[1].split(":");
            try {
                List<String> list = new ArrayList<>();
                for (String s : libs[1].split(",")) {
                    list.add(s);
                }
                userInfo.put(key, list);
            } catch (IndexOutOfBoundsException e) {
                userInfo.put(key, null);
            }
        }
    }

    public Map<String, List<String>> getUserInfo() {
        return userInfo;
    }


    public void addLibraryInUserInfo(String username, String library) {
        List<String> userLibraies = userInfo.get(username);

        if (userLibraies == null) {
            userLibraies = new ArrayList<>();
        }

        userLibraies.add(library);
        userInfo.put(username, userLibraies);
    }

    public List<String> getUserInfoForDiskWrite() {

        List<String> listUserInfo = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : userInfo.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append("user:"+entry.getKey());
            sb.append(";libraries:");
            List<String> libs = entry.getValue();
            
            if (libs == null) {}
            else {
                for (int i = 0; i < libs.size(); i++) {
                    sb.append(libs.get(i));
                    if (i < libs.size() - 1) {
                        sb.append(",");
                    }
                }
            }
            listUserInfo.add(sb.toString());
        }
        return listUserInfo;
    }
}
