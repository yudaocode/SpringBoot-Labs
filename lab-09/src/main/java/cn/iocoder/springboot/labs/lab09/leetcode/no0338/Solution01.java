package cn.iocoder.springboot.labs.lab09.leetcode.no0338;

import java.util.Arrays;

public class Solution01 {

    public int[] countBits(int num) {
        int[] results = new int[num + 1];
        results[0] = 0;

        // 计算每个位置的 bits 数量
        for (int i = 1; i <= num; i++) {
            int bits = i & 1; // 计算最后一位，是否为负数
            results[i] = bits + results[i >> 1];
        }

        return results;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution01().countBits(5)));
    }

}
