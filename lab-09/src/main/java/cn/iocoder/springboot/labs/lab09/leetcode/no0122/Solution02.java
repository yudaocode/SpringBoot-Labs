package cn.iocoder.springboot.labs.lab09.leetcode.no0122;

/**
 * DP 算法实现
 */
public class Solution02 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int zero = 0; // 无持有
        int one = -prices[0]; // 持有股票

        // 遍历，进行买卖
        for (int i = 1; i < prices.length; i++) {
            // 尝试卖出
//            if (one + (prices[i]) > zero) {
//                zero = one + prices[i];
//            }
            zero = Math.max(zero, one + prices[i]); // 简化
            // 尝试买入
//            if (zero - prices[i] > one) {
//                one = zero - prices[i];
//            }
            one = Math.max(one, zero - prices[i]); // 简化
        }

        return Math.max(one, zero);
    }

}
