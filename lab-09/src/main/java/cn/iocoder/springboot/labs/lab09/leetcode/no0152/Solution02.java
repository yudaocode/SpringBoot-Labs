package cn.iocoder.springboot.labs.lab09.leetcode.no0152;

/**
 * dp 方程
 *
 * dp1[i] = max(dp[i - 1] * nums[i], dp2[i -1] * nums[i], nums[i])
 * dp2[i] = min(dp[i - 1] * nums[i], dp2[i -1] * nums[i], nums[i])
 *
 * 通过不断求，使用上当前数值时，能产生的最大值。
 *
 * 注意，结果不是 dp1[n - 1] ，而是在这个过程中，产生的最大值。因为 nums 有负数的可能性，导致不一定使用上当前值，就一定是最大值。
 */
public class Solution02 {

    public int maxProduct(int[] nums) {
        // 最大值
        int max = nums[0];
        // 使用上当前位置的数，最大值。max 和 dp1 并不等价，因为使用上当前数字后，可能不一定大于 max
        int[] dp1 = new int[nums.length];
        dp1[0] = nums[0];
        // 使用上当前位置的数，最小值。使用最小值的原因是，可以有机会负负得正
        int[] dp2 = new int[nums.length];
        dp2[0] = nums[0];

        // 开始 dp
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            dp1[i] = max(dp1[i - 1] * num, // 如果 num 是正数的情况下，乘以 dp1，会变大
                    dp2[i - 1] * num, // 如果 num 是负数的情况，乘以 dp2 ，可能反倒比 dp1 大。
                    num); // 可能上面两个，乘了半天，还不如
            dp2[i] = min(dp1[i - 1] * num, dp2[i - 1] * num, num); // 同上面的想法
            // 求最大值
            max = Math.max(dp1[i], max);
        }

        return max;
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public static void main(String[] args) {
        Solution02 solution = new Solution02();
        System.out.println(solution.maxProduct(new int[]{2, 3, -2, -4}));
        System.out.println(solution.maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(solution.maxProduct(new int[]{-2, 0, 1}));
        System.out.println(solution.maxProduct(new int[]{0, 2}));
        System.out.println(solution.maxProduct(new int[]{2, -5, -2, -4, 3}));
        System.out.println(solution.maxProduct(new int[]{-1, -2, -9, -6}));
    }

}
