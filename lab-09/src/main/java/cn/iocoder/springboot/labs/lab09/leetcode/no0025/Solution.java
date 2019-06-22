package cn.iocoder.springboot.labs.lab09.leetcode.no0025;

class Solution {
    private ListNode swap(ListNode first, ListNode head, ListNode last) {
        ListNode originCurrent = head;

        // 先设置 first 指向 last
//        if (first != null) {
//            first.next = last;
//        }

        // 进行反序
        ListNode newHead = null;
        ListNode tail = last.next; // 用于判断结尾
        while (head != tail) {
            ListNode current = head;
            head = head.next;
            // 反向指向
            current.next = newHead;
            newHead = current;
        }

        // 原始的头，指向 tail
        originCurrent.next = tail;

        // first 指向新的头
        if (first != null) {
            first.next = newHead;
        }

        // 返回结果
        return newHead;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode tmp = head;

        ListNode first = null; // 记录每个 N 个一组的前一个节点。
        ListNode current = head; // 记录 N 组的头节点。
        ListNode newHead = null; // 因为反转，所以可能产生新的头
        int counts = 0; // 计数 N
        while (tmp != null) {
            // 每 N 个形成一组，进行交换。
            counts++;
            if (counts == k) {
                ListNode tail = tmp.next;
                ListNode result = swap(first, current, tmp);
                if (newHead == null) { // 第一次反转的结果，成为新的头。
                    newHead = result;
                }
                counts = 0;
                // 设置下一组
                first = current;
                current = tail;
                tmp = tail;
            } else {
                // 跳到下一个节点
                tmp = tmp.next;
            }
        }

        return newHead != null ? newHead : head;
    }

    public static void main(String[] args) {
        if (false) {
            ListNode head = new ListNode(1);
            int[] values = new int[]{2, 3, 4, 5};
            ListNode current = head;
            for (int value : values) {
                current.next = new ListNode(value);
                current = current.next;
            }
            ListNode result = new Solution().reverseKGroup(head, 3);
            System.out.println(result);
        }
        if (true) {
            ListNode head = new ListNode(1);
            int[] values = new int[]{2, 3, 4};
            ListNode current = head;
            for (int value : values) {
                current.next = new ListNode(value);
                current = current.next;
            }
            ListNode result = new Solution().reverseKGroup(head, 2);
            System.out.println(result);
        }
    }

}
