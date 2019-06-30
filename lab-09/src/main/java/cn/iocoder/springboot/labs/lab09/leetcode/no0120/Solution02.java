package cn.iocoder.springboot.labs.lab09.leetcode.no0120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自下而上，进行 DP
 */
public class Solution02 {

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty()) {
            return 0;
        }

        // 通过 ways ，记录最大路径
        int size = triangle.size();
        int[] ways = new int[triangle.get(size - 1).size()];
        for (int j = 0; j < triangle.get(size - 1).size(); j++) {
            ways[j] = triangle.get(size - 1).get(j);
        }

        // 自下而上 DP
        for (int i = size - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                ways[j] = Math.min(ways[j], ways[j + 1]) + triangle.get(i).get(j);
            }
        }

        return ways[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(new Solution02().minimumTotal(triangle));
    }

}
