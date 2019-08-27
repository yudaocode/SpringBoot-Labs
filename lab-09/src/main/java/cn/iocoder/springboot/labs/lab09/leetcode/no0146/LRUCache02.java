package cn.iocoder.springboot.labs.lab09.leetcode.no0146;

import java.util.HashMap;
import java.util.Map;

public class LRUCache02 {

    private class Node {

        private int key;
        private int value;
        private Node prev;
        private Node next;

        public Node(int key, int value, Node prev, Node next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

    }

    /**
     * 容量
     */
    private int capacity;
    /**
     * 缓存
     */
    private Map<Integer, Node> cache;
    /**
     * 指向头
     */
    private Node head;
    /**
     * 指向尾.
     *
     * 最新的元素，放在尾部
     */
    private Node tail;

    public LRUCache02(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity, 1F);
    }

    public int get(int key) {
        Node node = this.getNode(key);
        return node != null ? node.value : -1;
    }

    public void put(int key, int value) {
        Node node = getNode(key);

        // 获得到值，设置其值
        if (node != null) {
            node.value = value;
            return;
        }

        // 移除最老访问的
        removeIfFull();

        // 容量足够
        node = new Node(key, value, tail, null);
        cache.put(key, node);
        // 设置新的尾节点
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    private Node getNode(int key) {
        // head 为空，说明无元素
        if (head == null) {
            return null;
        }

        // 获得元素
        Node node = cache.get(key);
        if (node == null) { // 如果获得不到，返回空
            return null;
        }

        // 如果尾部并未指向 node ，则重新指向
        if (node != tail) {
            if (node == head) {
                head = head.next;
                head.prev = null;
            } else {
                node.next.prev = node.prev;
                node.prev.next = node.next;
            }

            // 添加到尾部
            node.prev = tail;
            tail.next = node;
            node.next = null;
            tail = node;
        }

        return node;
    }

    private void removeIfFull() {
        if (cache.size() < capacity) {
            return;
        }

        // 移除 cache
        cache.remove(head.key);
        // 更新新的 head
        head = head.next;
        if (head != null) { // 主要为了处理 capacity 为 1 的情况
            head.prev = null;
        }
    }

    public static void main(String[] args) {
        if (false) {
            LRUCache02 cache = new LRUCache02(2 /* 缓存容量 */);

            cache.put(1, 1);
            cache.put(2, 2);
            System.out.println(cache.get(1));       // 返回  1
            cache.put(3, 3);    // 该操作会使得密钥 2 作废
            System.out.println(cache.get(2));       // 返回 -1 (未找到)
            cache.put(4, 4);    // 该操作会使得密钥 1 作废
            System.out.println(cache.get(1));       // 返回 -1 (未找到)
            System.out.println(cache.get(3));       // 返回  3
            System.out.println(cache.get(4));      // 返回  4
        }
        if (true) {
            LRUCache02 cache = new LRUCache02(1 /* 缓存容量 */);
            cache.put(2, 1);
            System.out.println(cache.get(2));       // 返回  1
            cache.put(3, 2);
            System.out.println(cache.get(2));       // 返回
            System.out.println(cache.get(3));       // 返回
        }
    }

}
