package cn.iocoder.springboot.labs.lab09.leetcode.no0152;

@Deprecated // 解法不对
public class Solution01 {

    public int maxProduct(int[] nums) {
        Integer max = Integer.MIN_VALUE; // 最大值
        Integer x = null; // 正数
        Integer y = null; // 负数

        // 遍历
        for (int num : nums) {
            if (num >= 0) { // 正数
                if (x == null) {
                    x = num;
                } else {
                    if (num * x > x) {
                        x = num * x;
                    } else {
                        x = num;
                    }
                }
                if (y != null) {
                    if (num == 0) {
                        y = null;
                    } else {
                        y = y * num;
                    }
                }
            } else { // 负数
                if (y == null) {
                    if (x == null) {
                        y = num;
                    } else {
                        y = num * x;
                        x = null;
                    }
                } else {
                    y = num * y;
                    if (y > 0) {
                        x = y;
                        y = num;
                    }
                }
            }
            // 判断是否超过
            if (x != null && x > max) {
                max = x;
            }
            if (y != null && y > max) { // 处理，整个数组只有一个负数的情况。
                max = y;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Solution01 solution = new Solution01();
//        System.out.println(solution.maxProduct(new int[]{2, 3, -2, -4}));
//        System.out.println(solution.maxProduct(new int[]{2, 3, -2, 4}));
//        System.out.println(solution.maxProduct(new int[]{-2, 0, 1}));
//        System.out.println(solution.maxProduct(new int[]{0, 2}));
//        System.out.println(solution.maxProduct(new int[]{2, -5, -2, -4, 3}));
        System.out.println(solution.maxProduct(new int[]{-1, -2, -9, -6}));
    }

}
