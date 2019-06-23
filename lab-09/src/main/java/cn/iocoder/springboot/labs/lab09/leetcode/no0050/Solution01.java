package cn.iocoder.springboot.labs.lab09.leetcode.no0050;

class Solution01 {

    public double myPow(double x, int n) {
        // 递归结束
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }

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

        // 递归处理
        if (n % 2 == 0) {
            double result = myPow(x, n / 2);
            return result * result;
        } else {
            n = n - 1;
            double result = myPow(x, n / 2);
            return x * result * result;
        }
    }

}
