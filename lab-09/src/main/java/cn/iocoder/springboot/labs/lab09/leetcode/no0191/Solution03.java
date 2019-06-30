package cn.iocoder.springboot.labs.lab09.leetcode.no0191;

public class Solution03 {

    public int hammingWeight(int n) {
        int mask = 1;
        int weight = 0;

        for (int i = 0; i < 32; i++) { // 32 的原因是，int 是 32 位。
            if ((n & mask) == mask) { // 其它位都被 & 掉后，直接等于 mask 了，如果当前位是 1 的话 ，或者换个写法 (n & mask) != 0
                weight++;
            }
            mask = mask << 1; // 左移一位，准备和下一位对比
        }

        return weight;
    }

    public static void main(String[] args) {
//        int n = 0b11111111000000001111111100000000;
//        System.out.println(new Solution03().hammingWeight(n));
//        System.out.println(new Solution03().hammingWeight(3));
//        System.out.println(new Solution03().hammingWeight(-3));
        System.out.println(new Solution03().hammingWeight(Integer.MIN_VALUE));
    }

}
