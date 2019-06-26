package cn.iocoder.springboot.labs.lab09.leetcode.no0035;

import java.util.ArrayList;
import java.util.List;

/**
 * 在 {@link Solution01} 的基础上，做一个优化
 */
public class Solution02 {

    public List<List<String>> solveNQueens(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        // 初始化变量
        List<List<String>> results = new ArrayList<>();
        int[] used = new int[n]; // 标记，指定层，使用的位置
        for (int i = 0; i < n; i++) {
            used[i] = -1;
        }
        boolean[] usedCol = new boolean[n]; // 使用的列的标记
        boolean[] usedSum = new boolean[2 * n]; // x + y = c ，即“撇” 。
        boolean[] usedMinus = new boolean[2 * n]; // x - y = c ，即“捺” 。


        // 递归
        gen(results, used, usedCol, usedSum, usedMinus,  0, n);

        // 返回结果
        return results;
    }

    private void gen(List<List<String>> results, int[] used, boolean[] usedCol, boolean[] usedSum, boolean[] usedMinus, int index, int max) {
        // 到达最大值，结束递归
        if (index == max) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < max; i++) {
                StringBuilder str = new StringBuilder();
                for (int j = 0; j < max; j++) {
                    if (used[i] == j) {
                        str.append("Q");
                    } else {
                        str.append(".");
                    }
                }
                result.add(str.toString());
            }
            results.add(result);
            return;
        }

        // 递归
        for (int i = 0; i < max; i++) { // i 表示第几个位置
            boolean isOK = true;
            // 判断该位置，是否可用
            if (usedCol[i]) { // 列已经使用
                continue;
            }
            int usedSumIndex = index + i; // “撇”已经使用
            if (usedSum[usedSumIndex]) {
                continue;
            }
            int usedMinusIndex = max + index - i; // “捺”已经使用
            if (usedMinus[usedMinusIndex]) {
                continue;
            }
            // 标记已使用
            used[index] = i;
            usedCol[i] = true;
            usedSum[usedSumIndex] = true;
            usedMinus[usedMinusIndex] = true;
            // 递归
            gen(results, used, usedCol, usedSum, usedMinus, index + 1, max);
            // 标记未使用，继续循环
            used[index] = -1;
            usedCol[i] = false;
            usedSum[usedSumIndex] = false;
            usedMinus[usedMinusIndex] = false;

        }
    }

}
