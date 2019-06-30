package cn.iocoder.springboot.labs.lab09.leetcode.no0070;

public class Solution {

    public int climbStairs(int n) {
        int f1 = 1; // 走一步
        int f2 = 1; // 走两步
        for (int i = 2; i <= n; i++) {
            int fi = f1 + f2;
            // 切换值
            f1 = f2;
            f2 = fi;
        }
        return f2;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.climbStairs(1));
        System.out.println(solution.climbStairs(2));
        System.out.println(solution.climbStairs(3));
        System.out.println(solution.climbStairs(4));
        System.out.println(solution.climbStairs(5));
    }

}
