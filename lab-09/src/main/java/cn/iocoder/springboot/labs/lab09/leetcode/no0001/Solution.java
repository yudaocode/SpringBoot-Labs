package cn.iocoder.springboot.labs.lab09.leetcode.no0001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {

//    public int[] twoSum(int[] nums, int target) {
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                int sum = nums[i] + nums[j];
//                if (sum == target) {
//                    return new int[]{i, j};
//                }
//            }
//        }
//        return new int[]{0};
//    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>(nums.length, 1.0F);
        for (int i = 0; i < nums.length; i++) {
            // 避免相同数字相加，恰好相等
            Integer j = numMap.get(target - nums[i]);
            if (j != null) {
                return new int[]{j, i};
            }
            numMap.put(nums[i], i);
        }
        return new int[]{0};
    }

    public static void main(String[] args) {
//        int[] result = new Solution().twoSum(new int[]{0, 4, 3, 0}, 0);
//        System.out.println(Arrays.toString(result));
        int[] result = new Solution().twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(result));
//        int[] result = new Solution().twoSum(new int[]{-1, -2, -3, -4, -5}, -8);
//        System.out.println(Arrays.toString(result));
    }

}
