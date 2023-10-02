package com.memmorise.app.interective.learning;

import java.util.Map;

public class FirstLevel {
    
    
    private Map<String, String> currentLib;
    private LearnMap<Integer, String> help;

    public FirstLevel(Map<String, String> currentLib) {
        this.currentLib = currentLib;
        run();
    }


    private void run() {

    }

    private void prepereHelp() {
        for (Map.Entry<String, String> entry : currentLib.entrySet()) {
            
        }
    }
}
