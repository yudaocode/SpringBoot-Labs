package cn.iocoder.springboot.labs.lab09.leetcode.no0121;

/**
 * 贪心算法实现
 */
public class Solution02 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int max = 0;
        int buy = prices[0];

        // 遍历，进行买卖
        for (int i = 1; i < prices.length; i++) {
            // 计算，当前位置，卖出，能赚多少钱。
            max = Math.max(max, prices[i] - buy);
            // 计算当前位置值得买不
            buy = Math.min(buy, prices[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        Solution02 solution = new Solution02();
        System.out.println(solution.maxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println(solution.maxProfit(new int[]{7,1,5,3,6,4,7}));
        System.out.println(solution.maxProfit(new int[]{7,6,4,3,1}));
    }

}
