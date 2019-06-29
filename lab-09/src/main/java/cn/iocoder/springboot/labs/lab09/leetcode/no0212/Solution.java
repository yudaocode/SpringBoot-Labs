package cn.iocoder.springboot.labs.lab09.leetcode.no0212;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {

    public class TrieNode {

        private TrieNode[] children;
        private boolean end;

        public TrieNode() {
            children = new TrieNode['z' - 'a' + 1];
        }

    }

    private TrieNode node;
    private int[] maxes = new int['z' - 'a' + 1];

    /** Initialize your data structure here. */
    public Solution() {
        node = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        // 求每个字符串开头字母的最大值
        int max = maxes[getIndex(word.charAt(0))];
        if (max < word.length()) {
            maxes[getIndex(word.charAt(0))] = word.length();
        }

        // 遍历，创建节点
        TrieNode current = node;
        for (int i = 0; i < word.length(); i++) {
            int index = getIndex(word.charAt(i));
            TrieNode next = current.children[index];
            if (next == null) {
                next = new TrieNode();
                current.children[index] = next;
            }
            current = next;
        }

        // 标记结束
        current.end = true;
    }

    private int getIndex(char ch) {
        return ch - 'a';
    }

    public List<String> findWords(char[][] board, String[] words) {
        // 创建 word 的字典树
        for (String word : words) {
            insert(word);
        }

        // 创建返回结果
        List<String> results = new ArrayList<>();

        // 通过 bfs 遍历
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int maxLength = maxes[getIndex(board[i][j])];
                dfs(results, "", maxLength, node, board, i, j);
            }
        }

        // 排重
        return new ArrayList<>(new HashSet<>(results));
    }

    private void dfs(List<String> results, String str, int maxLength, TrieNode current, char[][] board, int i, int j) {
        // 如果 str 超过 maxCounts 大小，说明超过要获取字符串的最大长度，结束
        if (str.length() >= maxLength) {
            return;
        }

        // 获得当前
        char ch = board[i][j];
        TrieNode next = current.children[getIndex(ch)];
        if (next == null) {
            return;
        }
        if (next.end) { // 找到
            results.add(str + ch);
            // 这里，注意一定不要结束，可能还有继续的
        }
        // 标记为 # ，代表已经访问
        str += ch;
        board[i][j] = '#';

        // 继续 dfs ，循环周边的节点
        int n = board.length;
        int m = board[0].length;
        int[][] positions = {{i - 1, j}, {i + 1, j}, {i, j - 1}, {i, j + 1}};
        for (int[] position : positions) {
            int x = position[0], y = position[1];
            // 跳过越界的节点
            if (x >= n || x < 0 || y >= m || y < 0) {
                continue;
            }
            // 判断是否之前已经访问过
            char nextCh = board[x][y];
            if (nextCh == '#') {
                continue;
            }
            // dfs 遍历
            dfs(results, str, maxLength, next, board, x, y);
        }

        // 重置
        board[i][j] = ch;
    }

    public static void main(String[] args) {
        if (false) {
            char[][] boards = {
                    {'o', 'a', 'a', 'n'},
                    {'e', 't', 'a', 'e'},
                    {'i', 'h', 'k', 'r'},
                    {'i', 'f', 'l', 'v'}
            };
            String[] words = new String[]{"oath", "pea", "eat", "rain"};

            Solution solution = new Solution();
            List<String> results = solution.findWords(boards, words);
            System.out.println(results);
        }

        if (false) {
            char[][] boards = {
                    {'a', 'b'},
                    {'c', 'd'}
            };
            String[] words = new String[]{"acdb"};

            Solution solution = new Solution();
            List<String> results = solution.findWords(boards, words);
            System.out.println(results);
        }

        if (false) {
            char[][] boards = {
                    {'a', 'b'},
                    {'c', 'd'}
            };
            String[] words = new String[]{"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"};
            Solution solution = new Solution();
            List<String> results = solution.findWords(boards, words);
            System.out.println(results);
        }

        if (true) {
            char[][] boards = {
                    {'a', 'b'},
                    {'a', 'a'}
            };
            String[] words = new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"};
            Solution solution = new Solution();
            List<String> results = solution.findWords(boards, words);
            System.out.println(results);
//            ["aaa","aba","aaba","baa"]
//            ["aaa","aaab","aaba","aba","baa"]
            // 缺，aaab
        }


    }

}
