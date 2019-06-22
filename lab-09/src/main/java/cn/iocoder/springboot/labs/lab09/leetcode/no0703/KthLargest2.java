package cn.iocoder.springboot.labs.lab09.leetcode.no0703;

import java.util.PriorityQueue;
import java.util.Queue;

class KthLargest2 {

    private Queue<Integer> queue;
    private int k;

    public KthLargest2(int k, int[] nums) {
        queue = new PriorityQueue<>(k);
        this.k = k;
        // 初始化
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (queue.size() < k) {
            // 赋值
            queue.add(val);
        } else {
            if (val > queue.peek()) { // 大于最小值，才有资格加入其中。因为我们构建的 heap 是小顶堆，最上面存储的是第 k 大。
                queue.poll();
                queue.add(val);
            }
        }

        return queue.peek();
    }

    public static void main(String[] args) {
        if (true) {
            int k = 3;
            int[] nums = {4, 5, 8, 2};
            KthLargest2 kthLargest = new KthLargest2(k, nums);
            System.out.println(kthLargest.add(3));
            System.out.println(kthLargest.add(5));
            System.out.println(kthLargest.add(10));
            System.out.println(kthLargest.add(9));
            System.out.println(kthLargest.add(4));
        }
        if (false) {
            int k = 1;
            int[] nums = {};
            KthLargest2 kthLargest = new KthLargest2(k, nums);
            System.out.println(kthLargest.add(-3));
            System.out.println(kthLargest.add(-2));
            System.out.println(kthLargest.add(-4));
            System.out.println(kthLargest.add(0));
            System.out.println(kthLargest.add(4));
        }
    }

}
