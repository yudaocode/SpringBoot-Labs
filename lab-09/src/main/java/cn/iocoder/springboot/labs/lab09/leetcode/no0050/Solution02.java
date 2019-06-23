package cn.iocoder.springboot.labs.lab09.leetcode.no0050;

class Solution02 {

    public double myPow(double x, int n) {
        // 处理
        if (n < 0) {
            x = 1 / x;
            // 处理当 n 是负的最大值，导致的取反，还是其自身的问题
            if (n == Integer.MIN_VALUE) {
                x = x * x;
                n = Integer.MAX_VALUE;
            } else {
                n = -n;
            }
        }

        // 循环处理
        double pow = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                pow = pow * x;
            }
            x = x * x;
            n = n / 2;
        }

        return pow;
    }

}
