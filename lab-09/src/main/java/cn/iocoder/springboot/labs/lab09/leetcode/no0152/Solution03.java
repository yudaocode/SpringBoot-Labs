package cn.iocoder.springboot.labs.lab09.leetcode.no0152;

/**
 * {@link Solution02} 的改进，不使用两个一维数组，而是直接使用两个变量。
 */
public class Solution03 {

    public int maxProduct(int[] nums) {
        // 最大值
        int max = nums[0];
        // 使用上当前位置的数，最大值。max 和 dp1 并不等价，因为使用上当前数字后，可能不一定大于 max
        int dp1 = nums[0];
        // 使用上当前位置的数，最小值。使用最小值的原因是，可以有机会负负得正
        int dp2 = nums[0];

        // 开始 dp
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int[] results = minAndMax(dp1 * num, dp2* num, num);
            dp1 = results[1];
            dp2 = results[0];
            // 求最大值
            max = Math.max(dp1, max);
        }

        return max;
    }

    private int[] minAndMax(int a, int b, int c) {
        return new int[]{Math.min(Math.min(a, b), c),
                Math.max(Math.max(a, b), c)};
    }

    public static void main(String[] args) {
        Solution03 solution = new Solution03();
        System.out.println(solution.maxProduct(new int[]{2, 3, -2, -4}));
        System.out.println(solution.maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(solution.maxProduct(new int[]{-2, 0, 1}));
        System.out.println(solution.maxProduct(new int[]{0, 2}));
        System.out.println(solution.maxProduct(new int[]{2, -5, -2, -4, 3}));
        System.out.println(solution.maxProduct(new int[]{-1, -2, -9, -6}));
    }

}
