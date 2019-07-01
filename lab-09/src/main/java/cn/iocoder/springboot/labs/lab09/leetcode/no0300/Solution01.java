package cn.iocoder.springboot.labs.lab09.leetcode.no0300;

import java.util.Arrays;

/**
 * DP 实现，时间复杂度为 O(N^2) 。
 *
 * DP 转换方程是：
 *  dp[i] = MAX(dp[i], dp[j] + 1)
 *  其中，dp[i] 表示，截止目前，最大的上升的子序列
 */
public class Solution01 {

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dps = new int[nums.length];
        Arrays.fill(dps, 1); // 都赋值为 1 先，因为自己是自己的递增。
        int result = 1;

        // dp
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dps[i] = Math.max(dps[i], dps[j] + 1);
                }
            }
            result = Math.max(result, dps[i]);
        }

        return result;
    }

}
