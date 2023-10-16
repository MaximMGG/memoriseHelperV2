package com.memmorise.app.interective.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.memmorise.app.library.Library;

public class LearnMap {

    public List<Node> map;
    private int length;

    public void setMap(Library lib, int levelOfNow) {
        map = new ArrayList<>();
        length = 0;
        for(Map.Entry<String, String> entry : lib.getLibraryContent().entrySet()) {
            map.add(new Node(levelOfNow, entry.getKey(), entry.getValue()));
            length++;
        }
    }

    public int size() {
        return length;
    }

    public class Node {
        public int levelOfNow;
        public String word;
        public String translation;
        public int mistakes;

        private Node(int levelOfNow, String word, String translation) {
            this.levelOfNow = levelOfNow;
            this.word = word;
            this.translation = translation;
            this.mistakes = 0;
        }
    }
}
