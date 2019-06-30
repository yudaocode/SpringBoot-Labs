package cn.iocoder.springboot.labs.lab09.leetcode.no0052;

class Solution01 {

    private int total = 0;

    public int totalNQueens(int n) {
        // 初始化变量
        int[] used = new int[n]; // 标记，指定层，使用的位置
        for (int i = 0; i < n; i++) {
            used[i] = -1;
        }

        // 递归
        gen(used, 0, n);

        // 返回结果
        return total;
    }

    private void gen(int[] used, int index, int max) {
        // 到达最大值，结束递归
        if (index == max) {
            total++;
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
            gen(used, index + 1, max);
            // 标记未使用，继续循环
            used[index] = -1;
        }
    }

}
