package misc;

import java.util.*;

class TrieNode {
    char ch;
    boolean isWord;
    Map<Character, TrieNode> children;
    TrieNode(char ch){
        this.ch = ch;
        children = new HashMap<>();
    }
}

public class WordSearch2 {

    static int[][] dirs = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public static void main(String[] args) {

        char[][] grid = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String[] words = {"ABCCED"};
        System.out.println(findWords(grid, words));
    }


    public static List<String> findWords(char[][] board, String[] words) {

        // put all the words in the trie
        TrieNode root = new TrieNode('\0');
        for(String word : words){
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

        List<String> ans = new ArrayList<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                findWord(board, root, ans, i, j, "");
            }
        }
        return ans;
    }

    public static void findWord(char[][] board, TrieNode node, List<String> ans, int i, int j, String str){

        if(node.isWord){
            ans.add(str);
            node.isWord = false;
        }
        if(i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] == '\0') return;
        char ch = board[i][j];
        board[i][j] = '\0';
        if(node.children.containsKey(ch)){
            for(int[] dir : dirs){
                findWord(board, node.children.get(ch), ans, i + dir[0], j + dir[1], str + ch);
            }
        }
        board[i][j] = ch;
    }
}
