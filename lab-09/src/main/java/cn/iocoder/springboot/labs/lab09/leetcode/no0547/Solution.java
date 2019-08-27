package cn.iocoder.springboot.labs.lab09.leetcode.no0547;

import java.util.HashSet;
import java.util.Set;

@Deprecated // 无法通过
public class Solution {

    public int findCircleNum(int[][] M) {
        int n = M.length;
        if (n == 0) {
            return 0;
        }

        // 初始化数据
        int[] friends = new int[n];
        for (int i = 0; i < n; i++) { // 自己指向自己
            friends[i] = i;
        }

        // 重新梳理指向
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) {
                    if (friends[j] == j) {
                        friends[j] = friends[i];
                    } else {
                        // 向上设置其它节点，合并
                        int k = j;
                        while (true) {
                            int old = friends[k];
                            friends[k] = friends[i];
                            if (old == k) {
                                break;
                            }
                            k = old;
                        }
                    }
                }
            }
        }

        // 计算结果
        Set<Integer> root = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (friends[i] != i) {
                continue;
            }
            root.add(friends[i]);
        }
        return root.size();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.findCircleNum(new int[][]{
//                {1,1,0},
//                {1,1,0},
//                {0,0,1}
//        }));
//        System.out.println(solution.findCircleNum(new int[][]{
//                {1,1,0},
//                {1,1,1},
//                {0,1,1}
//        }));
//        System.out.println(solution.findCircleNum(new int[][]{
//                {1,0,0,1},
//                {0,1,1,0},
//                {0,1,1,1},
//                {1,0,1,1}
//        }));
//        System.out.println(solution.findCircleNum(new int[][]{{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},{0,1,0,1,0,0,0,0,0,0,0,0,0,1,0},{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{0,1,0,1,0,0,0,1,0,0,0,1,0,0,0},{0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,0,1,1,0,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0},{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},{0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},{0,1,0,0,0,0,0,0,0,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}}));
        System.out.println(solution.findCircleNum(new int[][]{{1,1,0,0,0,0,0,1,0,0,0,0,0,0,0},{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,1,1,0,0,0,0,0,0,0,0},{0,0,0,0,1,0,0,0,0,1,1,0,0,0,0},{0,0,0,1,0,1,0,0,0,0,1,0,0,0,0},{0,0,0,1,0,0,1,0,1,0,0,0,0,1,0},{1,0,0,0,0,0,0,1,1,0,0,0,0,0,0},{0,0,0,0,0,0,1,1,1,0,0,0,0,1,0},{0,0,0,0,1,0,0,0,0,1,0,1,0,0,1},{0,0,0,0,1,1,0,0,0,0,1,1,0,0,0},{0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,1,0,1,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,0,1,0,0,0,0,1}}));
    }

}
