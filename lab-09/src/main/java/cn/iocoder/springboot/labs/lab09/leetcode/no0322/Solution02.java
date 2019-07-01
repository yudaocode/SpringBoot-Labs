package cn.iocoder.springboot.labs.lab09.leetcode.no0322;

import java.util.Arrays;

public class Solution02 {

    public int coinChange(int[] coins, int amount) {
        int[] min = new int[amount + 1];
        Arrays.fill(min, amount + 1); // 因为肯定不会使用到 amount + 1 个硬币。
        min[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                int index = i - coin;
                // 面额过大，大于 i 。
                if (index < 0) {
                    continue;
                }
                // 如果为 -1 ，说明没有这个组合
                min[i] = Math.min(min[i], min[index] + 1);
            }
        }

        return min[amount] <= amount ? min[amount] : -1;
    }

    public static void main(String[] args) {
        Solution02 solution = new Solution02();

        System.out.println(solution.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(solution.coinChange(new int[]{2}, 3));
    }

}
