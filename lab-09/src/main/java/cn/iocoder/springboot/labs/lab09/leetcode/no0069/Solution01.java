package cn.iocoder.springboot.labs.lab09.leetcode.no0069;

import java.util.Arrays;

/**
 * TODO 题号不对。
 */
class Solution01 {

    public int majorityElement(int[] nums) {
        // 排序
        Arrays.sort(nums);

        // 因为超过 n/2 个，所以返回中间
        return nums[nums.length / 2];
    }

}
