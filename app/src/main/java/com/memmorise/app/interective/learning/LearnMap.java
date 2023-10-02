package com.memmorise.app.interective.learning;

public class LearnMap<I, V> {

    private Node<I, V>[] node;
    private int index;



    public LearnMap() {
        node = new Node[10];
        index = 0;
    }

    public void put(I key, V firstVal, V secondVal) {
        node[index].key = key;
        node[index].firstVal = firstVal;
        node[index].secondVal = secondVal;
    }

    public V getFirstVal(I key) {

        Node<I, V> localNode = getNodeByKey(key);

        return localNode.firstVal;
    }
    
    public V getSecondVal(I key) {

        Node<I, V> localNode = getNodeByKey(key);

        return localNode.secondVal;
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
