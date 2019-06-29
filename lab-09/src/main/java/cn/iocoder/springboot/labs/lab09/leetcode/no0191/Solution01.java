package cn.iocoder.springboot.labs.lab09.leetcode.no0191;

public class Solution01 {

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int weight = 0;

//        if (n < 0) {
//            n = n - 1 + Integer.MAX_VALUE;
//        }

        // 计算有多少个 0
        while (n != 0) {
            n = n & (n - 1);
            weight++;
        }

        return weight;
    }

    public static void main(String[] args) {
        System.out.println(new Solution01().hammingWeight(Integer.MAX_VALUE));
        System.out.println(new Solution01().hammingWeight(-3));
    }

}
