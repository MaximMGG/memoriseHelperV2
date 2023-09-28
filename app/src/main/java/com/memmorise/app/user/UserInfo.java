package com.memmorise.app.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfo {

    private Map<String, List<String>> userConfig;
    private static User user;
    private static UserInfo instance = new UserInfo();
    
    private UserInfo() {}

    public static UserInfo getInstancse(){
        user = User.getInstance();
        return instance;
    }


    public void setUserConfig(List<String> userInfoFromDisk) {
        userConfig = new HashMap<>();
        for (String line : userInfoFromDisk) {
            String[] buffer = line.split(";");
            String key = buffer[0].split(":")[1];
            String[] libs = buffer[1].split(":");
            try {
                List<String> list = new ArrayList<>();
                for (String s : libs[1].split(",")) {
                    list.add(s);
                }
                userConfig.put(key, list);
            } catch (IndexOutOfBoundsException e) {
                userConfig.put(key, null);
            }
        }
        user.setUserInfo(this);
    }

    public Map<String, List<String>> getUserConfig() {
        return userConfig;
    }


    public void addLibraryInUserInfo(String username, String library) {
        if (userConfig == null) {
            userConfig = new HashMap<>();
        }
        List<String> userLibraies = userConfig.get(username);

        if (userLibraies == null) {
            userLibraies = new ArrayList<>();
        }

        userLibraies.add(library);
        userConfig.put(username, userLibraies);
    }

    public List<String> getUserInfoForDiskWrite() {

        List<String> listUserInfo = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : userConfig.entrySet()) {
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
