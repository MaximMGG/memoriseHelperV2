package com.memmorise.app.interective.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.memmorise.app.library.Library;

public class LearnMap {

    public List<Node> map;
    private int length;
    private List<Node> learnedWords;

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

    public int[] getNextPackOfWords(int packSize) {
        int pack[] = new int[packSize];

        if (map.size() - learnedWords.size() < packSize) {
            pack = new int[map.size() - learnedWords.size()];
        }

        if (learnedWords == null) learnedWords = new ArrayList<>();
        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < pack.length; i++) {
            int r = rand.nextInt(map.size());
            Node tmp = map.get(r);
            if (learnedWords.contains(tmp)) {
                if (learnedWords.size() == map.size()) {
                    return pack;
                }
                i--;
                continue;
            }
            pack[i] = r;
        }
        return pack;
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
