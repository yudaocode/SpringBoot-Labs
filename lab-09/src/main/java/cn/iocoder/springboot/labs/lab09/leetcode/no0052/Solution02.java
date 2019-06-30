package cn.iocoder.springboot.labs.lab09.leetcode.no0052;

/**
 * N 皇后问题，使用 bits 操作实现
 */
public class Solution02 {

    private int total;
    private int n;

    public int totalNQueens(int n) {
        this.total = 0;
        this.n = n;

        // 递归
        dfs(0, 0, 0, 0);

        // 返回结果
        return total;
    }

    public void dfs(int row, int cols, int pies, int nas) {
        if (row == n) {
            total++;
            return;
        }

        // 获得可选择的 Queue 的位置。
        int bits = (~(cols | pies | nas))  // | 操作后，就获得目前 Queen 可以攻击的位置；
                                            // ~ 取反，计算出目前可以放 Queen 的位置。
                & ((1 << n) - 1); // & 操作的原因，去掉 ~ 带来的多余的头部的 1 。
        while (bits > 0) {
            // 取最低位的 1
            int p = bits & (-bits);
            // dfs
            dfs(row + 1, // 进入下一行
                    cols | p, // | 操作的原因，当前位置被占用。
                    (pies | p) << 1, // 左移的原因，进入下一行后，/ 方向需要左移，那个位置无法放置。
                    (nas | p) >> 1 // 右移的原因，进入下一行后，\ 方向需要右移，那个位置无法放置。
            );
            // 去最低位的 1
            bits = bits & (bits - 1);
        }
    }

    public static void main(String[] args) {
        Solution02 solution02 = new Solution02();
        System.out.println(solution02.totalNQueens(3));
    }


}
