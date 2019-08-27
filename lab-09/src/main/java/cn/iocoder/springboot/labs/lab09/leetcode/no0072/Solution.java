package cn.iocoder.springboot.labs.lab09.leetcode.no0072;

public class Solution {

    public int minDistance(String word1, String word2) {
        // + 1 的原始是，
        //      1. 字符串的长度可以是 0 。例如说，word1 为 abc ，word2 为空串。
        //      2. 而数组的下标，代表的是第几个字符串。
        // 所以需要 + 1 。
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // 初始化
        for (int i = 0; i <= word1.length(); i++) { // i 变成 words2 0 的位置，就是意味着 words2 是空串，则需要不断删除
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) { // words[0] 变成 words2 j 的位置，就是意味着，需要不断添加。
            dp[0][j] = j;
        }

        // dp
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) { // -1 的原因，正如上面所说 + 1 的原因，dp 的下标，对应 word 的下标 - 1 。
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = this.min(
                            dp[i - 1][j] + 1, // 删除 a[i]
                            dp[i][j - 1] + 1, // 插入 b[j]
                            dp[i - 1][j - 1] + 1 // 替换 a[i] 到 b[j]
                    );
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minDistance("horse", "ros"));
    }

}
