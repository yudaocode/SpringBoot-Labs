package cn.iocoder.springboot.labs.lab09.leetcode.no0188;

/**
 * TODO 需要优化，会超过。例如说 k = 10 亿
 */
public class Solution {

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // 超过股票的一半，则只要买的有赚，就买入，第二天卖出。
        if (k >= prices.length / 2) {
            int result = 0;
            for (int i = 0; i < prices.length - 1; i++) {
                if (prices[i] < prices[i + 1]) {
                    result += prices[i + 1] - prices[i];
                }
            }
            return result;
        }

        // 第一维度 k ，表示当前【买入股票】的次数；
        // 第二维度 2 ，表示 0 - 未持有，1 - 持有 1 股
        // 值为最大利润
        int[][] dp = new int[k + 1][2];
        // 初始化第一个股票的处理
        for (int i = 1; i <= k; i++) {
            dp[i][1] = -prices[0]; // 买入
        }
        dp[0][1] = Integer.MIN_VALUE; // 相当于赋值为空，避免直接认为持有一股时，利润为 0 。

        // 遍历，进行买卖
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j <= k; j++) {
                // 尝试卖出
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                // 尝试买入
                if (j > 0) {
                    dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
                }
            }
        }

        // 求最大值
        int max = dp[0][0];
        for (int i = 1; i <= k; i++) {
            max = Math.max(max, dp[i][0]);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxProfit(2, new int[]{2, 4,1 }));
        System.out.println(solution.maxProfit(7, new int[]{3,2,6,5,0,3}));
        System.out.println(solution.maxProfit(2, new int[]{3,3,5,0,0,3,1,4}));
        System.out.println(solution.maxProfit(2, new int[]{1,2,3,4,5}));
        System.out.println(solution.maxProfit(2, new int[]{7, 6, 4, 3, 1}));
    }

}
