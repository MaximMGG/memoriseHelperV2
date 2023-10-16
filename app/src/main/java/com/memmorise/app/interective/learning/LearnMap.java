package com.memmorise.app.interective.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.memmorise.app.library.Library;

public class LearnMap {

    public List<Node> map;

    public void setMap(Library lib) {
        map = new ArrayList<>();
        int count = 0;
        for(Map.Entry<String, String> entry : lib.getLibraryContent().entrySet()) {
            map.add(new Node(count, entry.getKey(), entry.getValue()));
        }
    }




    public class Node {
        public int levelOfNow;
        public String word;
        public String translation;

        private Node(int levelOfNow, String word, String translation) {
            this.levelOfNow = levelOfNow;
            this.word = word;
            this.translation = translation;

        }
    }
}
