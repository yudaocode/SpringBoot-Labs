package cn.iocoder.springboot.labs.lab09.leetcode.no0322;

import java.util.Arrays;

public class Solution {

    public int coinChange(int[] coins, int amount) {
        int[] min = new int[amount + 1];
        Arrays.fill(min, -1);
        min[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            if (coin > amount) {
                continue;
            }
            min[coin] = 1;
        }

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                int index = i - coin;
                // 面额过大，大于 i 。
                if (index < 0) {
                    continue;
                }
                // 如果为 -1 ，说明没有这个组合
                if (min[index] == -1) {
                    continue;
                }
                if (min[i] == -1) {
                    min[i] = min[index] + 1;
                } else {
                    min[i] = Math.min(min[i], min[index] + 1);
                }

            }
        }

        return min[amount];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(solution.coinChange(new int[]{1, 2, 5}, 11));
    }

}
