package cn.iocoder.springboot.labs.lab09.leetcode.no0212;

import java.util.*;

public class Solution {

    public class TrieNode {

        private TrieNode[] children;
        private boolean end;

        public TrieNode() {
            children = new TrieNode['z' - 'a' + 1];
        }

    }

    public class QueueNode {

        /**
         * 第几个位置
         */
        private int index;
        /**
         * 在 board 中的 i 位置（行）
         */
        private int i;
        /**
         * 在 board 中的 j 位置（列）
         */
        private int j;
        /**
         * 字符
         */
        private char ch;
        /**
         * 从该节点，寻找是否有 {@link ch} 字符
         */
        private TrieNode trieNode;
        /**
         * 前置节点，即 bfs 访问到该节点
         */
        private QueueNode prevNode;

        public QueueNode(int i, int j, char ch, TrieNode trieNode, QueueNode prevNode) {
            this.index = prevNode == null ? 1 : prevNode.index + 1;
            this.i = i;
            this.j = j;
            this.ch = ch;
            this.trieNode = trieNode;
            this.prevNode = prevNode;
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
                findWord(results, board, i, j);
            }
        }

        // 排重
        return new ArrayList<>(new HashSet<>(results));
    }

    private void findWord(List<String> results, char[][] board, int i, int j) {
        // bfs ，用于记录访问过的节点
        int n = board.length;
        int m = board[0].length;
        boolean[][] visits = new boolean[n][m];

        // 获得这个字符串最大的长度
        int maxCounts = maxes[getIndex(board[i][j])];
        if (maxCounts == 0) {
            return;
        }

        // bfs 开始
        Queue<QueueNode> queue = new LinkedList<>();
        queue.add(new QueueNode(i, j, board[i][j], node, null));

        while (!queue.isEmpty()) {
            QueueNode queueNode = queue.poll();
            visits[queueNode.i][queueNode.j] = true;
            // 字典树遍历，判断当前节点，是否能从 trieNode 中找到。
            TrieNode current = queueNode.trieNode;
            TrieNode next = current.children[getIndex(queueNode.ch)];
            if (next == null) {
                continue;
            }
            // 找到，添加到 results 中，结束。
            if (next.end) {
                StringBuilder str = new StringBuilder();
                while (queueNode != null) {
                    str.insert(0, queueNode.ch);
                    queueNode = queueNode.prevNode;
                }
                results.add(str.toString());
                return;
            }

            // 如果当前节点的 index 到达 maxCounts ，就不在继续。
            if (queueNode.index == maxCounts) {
                continue;
            }

            // 循环周边的节点
            int[][] positions = {{queueNode.i - 1, queueNode.j}, {queueNode.i + 1, queueNode.j},
                    {queueNode.i, queueNode.j - 1}, {queueNode.i, queueNode.j + 1}};
            for (int p = 0; p < positions.length; p++) {
                int x = positions[p][0], y = positions[p][1];
                // 跳过越界的节点
                if (x >= n || x < 0) {
                    continue;
                }
                if (y >= m || y < 0) {
                    continue;
                }
                // 跳过已经访问过的情况
                if (visits[x][y]) {
                    continue;
                }
                queue.add(new QueueNode(x, y, board[x][y], next, queueNode));
            }
        }
    }

    public static void main(String[] args) {
        char[][] boards = {
              {'o','a','a','n'},
              {'e','t','a','e'},
              {'i','h','k','r'},
              {'i','f','l','v'}
            };
        String[] words = new String[]{"oath", "pea", "eat", "rain"};

        Solution solution = new Solution();
        List<String> results = solution.findWords(boards, words);
        System.out.println(results);
    }

}
