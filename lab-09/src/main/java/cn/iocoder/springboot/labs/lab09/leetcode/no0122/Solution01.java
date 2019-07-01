package cn.iocoder.springboot.labs.lab09.leetcode.no0122;

/**
 * 贪心算法实现
 */
public class Solution01 {

    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                result += prices[i + 1] - prices[i];
            }
        }
        return result;
    }

}
