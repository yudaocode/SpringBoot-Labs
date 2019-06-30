package cn.iocoder.springboot.labs.lab09.leetcode.no0338;

import java.util.Arrays;

public class Solution02 {

    public int[] countBits(int num) {
        int[] results = new int[num + 1];
        results[0] = 0;

        // 计算每个位置的 bits 数量
        for (int i = 1; i <= num; i++) {
            results[i] = 1 + results[i & (i - 1)]; // 和 Solution01
        }

        return results;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution02().countBits(5)));
    }

}
