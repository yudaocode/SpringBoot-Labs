package cn.iocoder.springboot.labs.lab09.leetcode.no0142;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    /**
     * 性能较差
     *
     * 每次走到下一个节点，都从头节点重新遍历，看看有没回环。
     *
     * @param head 头节点
     * @return 回环的点
     */
    public ListNode detectCycle01(ListNode head) {
        if (head == null) {
            return null;
        }
        // 寻找结尾
        ListNode current = head;
        while (current.next != null) {
            ListNode oldCurrent = current;
            ListNode tmpHead = head;
            // 跳到下一个
            current = current.next;
            // 处理，自己指向自己的情况
            if (current == oldCurrent) {
                return current;
            }
            // 从头结点开始走，判断是否有回环
            while (tmpHead != null) {
                //
                if (tmpHead == oldCurrent) {
                    break;
                }
                if (tmpHead == current) {
                    return current;
                }
                tmpHead = tmpHead.next;
            }
        }
        return null;
    }

    public ListNode detectCycle02(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<ListNode> visited = new HashSet<>();
        while (head != null && head.next != null) {
            visited.add(head);
            if (visited.contains(head.next)) {
                return head.next;
            }
            head = head.next;
        }
        return null;
    }

    private ListNode getIntersect(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }

    public ListNode  detectCycle03(ListNode head) {
        if (head == null) {
            return null;
        }

        // 先使用快慢指针，找到想交的节点
        ListNode intersect = getIntersect(head);
        if (intersect == null) { // 如果为空，说明不存在环
            return null;
        }

        // 找到环的入口
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
    }

    public ListNode detectCycle(ListNode head) {
//        return detectCycle01(head);
//        return detectCycle02(head);
        return detectCycle03(head);
    }

    public static void main(String[] args) {
        if (true) {
            ListNode head = new ListNode(1);
            ListNode tail = new ListNode(2);
            head.next = tail;
            tail.next = head;
            System.out.println(new Solution().detectCycle(head));
        }
        if (false) {
            ListNode head = new ListNode(1);
            head.next = head;
            System.out.println(new Solution().detectCycle(head));
        }
        if (false) {
            ListNode head = new ListNode(1);
            ListNode tail = new ListNode(2);
            head.next = tail;
            tail.next = tail;
            System.out.println(new Solution().detectCycle(head));
        }
        if (false) {
            ListNode head = new ListNode(1);
            ListNode tail = new ListNode(2);
            ListNode second = new ListNode(3);
            head.next = second;
            second.next = tail;
            tail.next = second;
            ListNode result = new Solution().detectCycle(head);
//            System.out.println();
            assert result == second;
        }
    }

}
