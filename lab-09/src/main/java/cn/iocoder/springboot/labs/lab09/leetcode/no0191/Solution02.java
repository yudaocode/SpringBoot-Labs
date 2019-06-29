package cn.iocoder.springboot.labs.lab09.leetcode.no0191;

public class Solution02 {

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int weight = 0;

        if (n < 0) {
            if (n == Integer.MIN_VALUE) { // 直接特殊处理，因为负数的最大值，取反超过了 int 的范围
                return 1;
            }
            // 负数，先通过 hammingWeight(-n) 求对应的正数的 0 的个数
            // 然后，再通过 32 - hammingWeight(-n) 取反，就是 0 的个数
            // 最后，因为要 + 1 ，所以要判断下 -n 最后一位是不是 0 ，如果是 0 ，+ 1 就会多一个 1 。
            return 32 - hammingWeight(-n) + (-n % 2);
        }

        // 计算有多少个 0
        while (n != 0) {
            if (n % 2 == 1) {
                weight++;
                System.out.print(1);
            } else {
                System.out.print(0);
            }
            n = n / 2;
        }

        return weight;
    }

    public static void main(String[] args) {
        System.out.println(new Solution02().hammingWeight(33423870));
//        System.out.println(new Solution02().hammingWeight(-3));
    }

}
