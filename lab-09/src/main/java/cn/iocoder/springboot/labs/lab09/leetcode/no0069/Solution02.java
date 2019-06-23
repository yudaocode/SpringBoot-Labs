package cn.iocoder.springboot.labs.lab09.leetcode.no0069;

public class Solution02 {

    public int majorityElement(int[] nums) {
        return find(nums, 0, nums.length - 1);
    }

    private int find(int[] nums, int low, int high) {
        if (low == high) {
            return nums[low];
        }

        // 分治
        int middle = (high - low) / 2 + low;
        int left = find(nums, low, middle);
        int right = find(nums, middle + 1, high);

        // 判断两表结果是否相等
        if (left == right) {
            return left;
        }

        int leftCounts = count(nums, left, low, middle);
        int rightCounts = count(nums, right, middle + 1, high);
        return leftCounts > rightCounts ? left : right;
    }

    private int count(int[] nums, int target, int start, int end) {
        int counts = 0;
        for (int i = start; i <= end; i++) {
            if (nums[i] == target) {
                counts++;
            }
        }
        return counts;
    }

}
