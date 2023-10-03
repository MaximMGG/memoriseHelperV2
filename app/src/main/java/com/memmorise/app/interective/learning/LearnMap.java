package com.memmorise.app.interective.learning;

public class LearnMap<I, V> {

    private Node<I, V>[] node;
    private int index;
    private int length;



    public LearnMap() {
        node = new Node[10];
        index = 0;
        length = 0;
    }

    public void put(I key, V firstVal, V secondVal) {
        node[index] = new Node<>();
        node[index].key = key;
        node[index].firstVal = firstVal;
        node[index].secondVal = secondVal;
        index++;
        length++;
    }

    public int length() {
        return length;
    }

    public V getFirstVal(I key) {

        Node<I, V> localNode = getNodeByKey(key);

        return localNode.firstVal;
    }
    
    public V getSecondVal(I key) {

        Node<I, V> localNode = getNodeByKey(key);

        return localNode.secondVal;
    }

    public boolean conteins(V v) {
        for (int i = 0; i < node.length; i++) {
            if(node[i].secondVal.equals(v)) {
                return true;
            }
        }
        return false;
    }

    private Node<I, V> getNodeByKey(I key) {
        for (int i = 0; i < node.length; i++) {
           if (node[i].key == key) {
                return node[i];
           }
        }
        return null;
    }


    private class Node<I, V> {
        public I key;
        public V firstVal;
        public V secondVal;
    }


}
