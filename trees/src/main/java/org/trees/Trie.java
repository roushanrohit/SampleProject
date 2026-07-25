package org.trees;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    char ch;
    Map<Character, TrieNode> children;
    boolean isWord;
    TrieNode(char ch){
        this.ch = ch;
        children = new HashMap<>(26);
    }
}

public class Trie {

    private TrieNode root;
    Trie(){
        root = new TrieNode('\0');
    }

    public void insert(String word){
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(!node.children.containsKey(ch)){
                node.children.put(ch, new TrieNode(ch));
            }
            node = node.children.get(ch);
        }
        node.isWord = true;
    }

    public boolean search(String word){
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(!node.children.containsKey(ch)){
                return false;
            }
            node = node.children.get(ch);
        }
        return node.isWord;
    }

    public boolean startsWith(String prefix){
        TrieNode node = root;
        for(int i = 0; i < prefix.length(); i++){
            char ch = prefix.charAt(i);
            if(!node.children.containsKey(ch)){
                return false;
            }
            node = node.children.get(ch);
        }
        return true;
    }

    public static void main(String[] args) {

        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("pen");
        System.out.println(trie.search("appl"));
        System.out.println(trie.startsWith("appl"));
    }
}
