package cn.iocoder.springboot.labs.lab09.leetcode.no0231;

public class Solution01 {

    public boolean isPowerOfTwo(int n) {
        // 非正整数，非正整数
        if (n <= 0) {
            return false;
        }

        int mask = 1;
        for (int i = 0; i < 32; i++) {
            // 或运算，如果就是自己，说明是 2 的 n 次方
            if ((n | mask) == mask) {
                return true;
            }

            // mask 超过范围，说明就是不符合
            if (mask > n) {
                return false;
            }

            mask = mask << 1;
        }

        return false;
    }

    public static void main(String[] args) {
        Solution01 solution = new Solution01();
        System.out.println(solution.isPowerOfTwo(1));
        System.out.println(solution.isPowerOfTwo(2));
        System.out.println(solution.isPowerOfTwo(3));
        System.out.println(solution.isPowerOfTwo(4));
        System.out.println(solution.isPowerOfTwo(5));
        System.out.println(solution.isPowerOfTwo(6));
    }

}
