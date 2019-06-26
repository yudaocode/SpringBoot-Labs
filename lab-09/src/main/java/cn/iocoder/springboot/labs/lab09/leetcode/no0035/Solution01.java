package cn.iocoder.springboot.labs.lab09.leetcode.no0035;

import java.util.ArrayList;
import java.util.List;

class Solution01 {

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

        // 递归
        gen(results, used, 0, n);

        // 返回结果
        return results;
    }

    private void gen(List<List<String>> results, int[] used, int index, int max) {
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
            for (int j = 0; j < index; j++) {
                // 纵方法，判断
                if (used[j] == i) {
                    isOK = false;
                    break;
                }
                // 斜方法，判断
                int diff = index - j; // 判断差的层级
                if (used[j] + diff == i || used[j] - diff == i) {
                    isOK = false;
                    break;
                }
            }
            // 如果不合法，continue
            if (!isOK) {
                continue;
            }
            // 标记已使用
            used[index] = i;
            // 递归
            gen(results, used, index + 1, max);
            // 标记未使用，继续循环
            used[index] = -1;
        }
    }

}
