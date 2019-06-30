package cn.iocoder.springboot.labs.lab09.leetcode.no0191;

/**
 * 注意，这个做法，处理负数，会有问题。所以看 {@link Solution03}
 */
public class Solution02 {

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int weight = 0;

        // 计算有多少个 0
        while (n != 0) {
            if (n % 2 == 1) {
                weight++;
//                System.out.print(1);
            } else {
//                System.out.print(0);
            }
            n = n / 2;
        }

        return weight;
    }

    public static void main(String[] args) {
        int n = 0b11111111000000001111111100000000;
        System.out.println(new Solution02().hammingWeight(n));
//        System.out.println(new Solution02().hammingWeight(-3));
    }

}
