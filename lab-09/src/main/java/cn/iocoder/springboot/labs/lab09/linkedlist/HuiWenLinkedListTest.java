package cn.iocoder.springboot.labs.lab09.linkedlist;

import java.util.Objects;

/**
 * 判断单链表，是否为回文结构
 *
 * 核心的逻辑是，顺序遍历，同时反向前半段。
 *
 * 对应极客时间《06 | 链表（上）：如何实现LRU缓存淘汰算法?》的思考题
 */
public class HuiWenLinkedListTest {

    public static class Node {

        private Object data;
        private Node next;

        public Object getData() {
            return data;
        }

        public Node setData(Object data) {
            this.data = data;
            return this;
        }

        public Node getNext() {
            return next;
        }

        public Node setNext(Node next) {
            this.next = next;
            return this;
        }
    }

    public static void main(String[] args) {
        huiwen01();
        huiwen02();
    }

    private static void huiwen01() {
        Node node01 = new Node().setData("1");
        Node node02 = new Node().setData("2");
        Node node03 = new Node().setData("3");
        Node node04 = new Node().setData("4");
        Node node05 = new Node().setData("5");
        node01.next = node02;
        node02.next = node03;
        node03.next = node04;
        node04.next = node05;

        assert !isHuiWen(node01);
    }

    private static void huiwen02() {
        Node node01 = new Node().setData("1");
        Node node02 = new Node().setData("2");
        Node node03 = new Node().setData("3");
        Node node04 = new Node().setData("2");
        Node node05 = new Node().setData("1");
        node01.next = node02;
        node02.next = node03;
        node03.next = node04;
        node04.next = node05;

        assert isHuiWen(node01);
    }

    private static boolean isHuiWen(Node head) {
        // 如果不存在节点，或者是单节点，不然是回文
        if (head == null || head.next == null) {
            return true;
        }

        // 通过下面的逻辑，将链表的前半段，反转。
        Node slow = head;
        Node fast = head; // fast 的用途，是能够保证只反向前半段
        Node prev = null; // 前半段的反向
        while (fast != null && fast.next != null) {
            // fast 节点，按照自己的节奏，每次走 2 步。
            fast = fast.next.next;
            // 创建 next 节点，记录 slow 真正下一个节点
            Node next = slow.next;
            // 开始反转
            slow.next = prev;
            prev = slow;
            // 设置 slow 为真正的下一个节点
            slow = next;
        }

        // 如果 fast 非空，说明 head 是基数个节点。此时，slow 需要跳过最中间的节点
        if (fast != null) {
            slow = slow.next;
        }

        // 开始对比 slow 和 prev
        while (slow != null && prev != null) {
            if (!Objects.equals(slow.data, prev.data)) {
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }

        return true;
    }

}
