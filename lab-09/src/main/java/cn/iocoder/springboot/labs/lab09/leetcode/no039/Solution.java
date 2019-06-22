package cn.iocoder.springboot.labs.lab09.leetcode.no039;

import java.util.*;

public class Solution {

    class Node {

        public int index;

        public int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Node> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>(nums.length - k + 1);
        for (int i = 0; i < nums.length; i++) {
            int maxValue = Integer.MIN_VALUE;
            if (!queue.isEmpty()) {
                // 移除超过范围的节点
                if (queue.get(0).index == i - k) {
                    queue.remove(0);
                }
                // 移除队列中，小于当前位置的元素
                ListIterator<Node> listiterator = queue.listIterator(queue.size()); // 倒序向前
                while (listiterator.hasPrevious()) {
                    Node node = listiterator.previous();
                    if (nums[i] >= node.value) {
                        listiterator.remove();
                    } else {
                        maxValue = node.value; // 说明，此值更大
                    }
                }
            }
            // 判断是否有更大的
            if (nums[i] > maxValue) {
                maxValue = nums[i];
            }
            // 添加到队尾
            queue.add(new Node(i, nums[i]));
            // 添加到结果
            if (i >= k -1) {
                result.add(maxValue);
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

//    public int[] maxSlidingWindow(int[] nums, int k) {
//        int[] num = nums;
//        int size = k;
//        //num就是nums，size就是k，res一开始也可以用数组
//        ArrayList<Integer> res = new ArrayList<>();
//        if(num == null || num.length == 0 || size <= 0 || size > num.length){
//            return res.stream().mapToInt(i -> i).toArray();
//        }
//        int left = 0, right = 0, max = num[0];
//        while(right < num.length){
//            while(right < num.length-1 && right - left < size-1){
//                right++;
//                if(num[right] > max){
//                    max = num[right];
//                }
//            }
//            res.add(max);
//            left++;
//            if(right == num.length-1) break;
//            if(num[left-1] == max){
//                right = left;
//                max = num[left];
//            }
//        }
//        return res.stream().mapToInt(i -> i).toArray();
//    }

    public static void main(String[] args) {
        if (false) {
            int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
            int k = 3;
            int[] result = new Solution().maxSlidingWindow(nums, k);
            System.out.println(Arrays.toString(result));
        }
        if (true) {
            int[] nums = {1, 3, 1, 2, 0, 5};
            int k = 3;
            int[] result = new Solution().maxSlidingWindow(nums, k);
            System.out.println(Arrays.toString(result));
        }
    }

//    private static void println(List<Node> queue) {
//        for (Node node : queue) {
//            System.out.print(" " + node.value);
//        }
//        System.out.println();
//    }

}
