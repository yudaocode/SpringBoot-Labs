package cn.iocoder.springboot.labs.lab09.leetcode.no0120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自上而下，进行 DP
 */
public class Solution01 {

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty()) {
            return 0;
        }
        // 只有一层，就不要求解了
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }

        // 通过 ways ，记录最大路径
        int size = triangle.size();
        int[] ways = new int[triangle.get(size - 1).size()];
        Arrays.fill(ways, Integer.MAX_VALUE);
        // 计算下一层的时候，临时使用
        int[] tmps = new int[ways.length];
        // 用于在最后一层，计算 ways 中的最小值。
        int min = Integer.MAX_VALUE;

        // 开始动态规划
        ways[0] = triangle.get(0).get(0);
        tmps[0] = triangle.get(0).get(0);
        for (int i = 1; i < size; i++) {
            int levelSize = triangle.get(i).size();
            for (int j = 0; j < levelSize; j++) {
                // 选取能到达当前节点的较小值
                int result;
                if (j == 0) {
                    result = ways[j];
                } else if (j == levelSize - 1) {
                    result = ways[j - 1];
                } else {
                    result = Math.min(ways[j], ways[j - 1]);
                }
                // 记录当前节点需要的最小路径，到 tmps 中。
                tmps[j] = result + triangle.get(i).get(j);
                // 如果是最后一层，开始计算最小值。
                if (i == size - 1) {
                    if (tmps[j] < min) {
                        min = tmps[j];
                    }
                }
            }
            System.arraycopy(tmps, 0, ways, 0, levelSize);
        }

        return min;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(new Solution01().minimumTotal(triangle));
    }

}
