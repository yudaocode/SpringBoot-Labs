package cn.iocoder.springboot.labs.lab09.leetcode.no0208;

public class Trie {

    public class Node {

        private Node[] children;
        private boolean end;

        public Node() {
            children = new Node['z' - 'a' + 1];
        }

    }

    private Node node;

    /** Initialize your data structure here. */
    public Trie() {
        node = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        // 遍历，创建节点
        Node current = node;
        for (int i = 0; i < word.length(); i++) {
            int index = getIndex(word.charAt(i));
            Node next = current.children[index];
            if (next == null) {
                next = new Node();
                current.children[index] = next;
            }
            current = next;
        }

        // 标记结束
        current.end = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        // 遍历，寻找节点
        Node current = node;
        for (int i = 0; i < word.length(); i++) {
            int index = getIndex(word.charAt(i));
            Node next = current.children[index];
            if (next == null) {
                return false;
            }
            current = next;
        }

        // 判断找到的节点，是否为根节点
        return current.end;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }

        // 遍历，寻找节点
        Node current = node;
        for (int i = 0; i < prefix.length(); i++) {
            int index = getIndex(prefix.charAt(i));
            Node next = current.children[index];
            if (next == null) {
                return false;
            }
            current = next;
        }

        // 结果找到
        return true;
    }

    private int getIndex(char ch) {
        return ch - 'a';
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app")); // 返回 true

        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 true

        System.out.println(trie.search("appled"));
    }

}
