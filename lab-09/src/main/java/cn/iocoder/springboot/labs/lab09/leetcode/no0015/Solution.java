package cn.iocoder.springboot.labs.lab09.leetcode.no0015;

import java.util.*;

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        // 排序
        Arrays.sort(nums);

        // 获得结果
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            // 遍历
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                // 符合条件
                int sum = nums[start] + nums[end];
                if (sum == target) {
                    // 添加到结果
                    results.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    // 增大 start
                    do {
                        start++;
                    } while (nums[start] == nums[start - 1] && start < end);
                    // 减小 end
                    do {
                        end--;
                    } while (nums[end] == nums[end + 1] && start < end);
                    continue;
                }
                // 小于预期，说明 start 对应的值太小，start 继续前进。
                if (sum < target) {
                    do {
                        start++;
                    } while (nums[start] == nums[start - 1] && start < end);
                // 大于预期，说明 end 对应的值太大，end 继续后退
                } else {
                    do {
                        end--;
                    } while (nums[end] == nums[end + 1] && start < end);
                }
            }
        }

        return results;
    }

    public List<List<Integer>> convert(Set<List<Integer>> result) {
        List<List<Integer>> newResult = new ArrayList<>(result.size());
        newResult.addAll(result);
        return newResult;
    }

    private List<List<Integer>> removeSome(List<List<Integer>> results) {
        // 排序
        for (List<Integer> result : results) {
            Collections.sort(result);
        }
        // 移除重复的
        List<List<Integer>> newResults = new ArrayList<>();
        for (List<Integer> result : results) {
            boolean exists = false;
            for (List<Integer> targetResult : newResults) {
                if (targetResult.equals(result)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                newResults.add(result);
            }
        }
        return newResults;
    }

//    public int[] twoSum(int[] nums, int start, int target) {
//        Map<Integer, Integer> numMap = new HashMap<>(nums.length, 1.0F);
//        for (int i = start; i < nums.length; i++) {
//            numMap.put(nums[i], i);
//        }
//        for (int i = start; i < nums.length; i++) {
//            // 避免相同数字相加，恰好相等
//            Integer j = numMap.get(target - nums[i]);
//            if (j != null) {
//                if (j == i) {
//                    continue;
//                }
//                return new int[]{i, j};
//            }
//        }
//        return new int[]{0};
//    }

    public static void main(String[] args) {
//        System.out.println(new Solution().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
//        System.out.println(new Solution().threeSum(new int[]{0,0,0,0}));
//        System.out.println(new Solution().threeSum(new int[]{-2, 0, 1, 1, 2}));
        System.out.println(new Solution().threeSum(new int[]{1,-1,-1,0}));
//        System.out.println(new Solution().threeSum(new int[]{-7,-11,12,-15,14,4,4,11,-11,2,-8,5,8,14,0,3,2,3,-3,-15,-2,3,6,1,2,8,-5,-7,3,1,8,11,-3,6,3,-4,-13,-15,14,-8,2,-8,4,-13,13,11,5,0,0,9,-8,5,-2,14,-9,-15,-1,-6,-15,9,10,9,-2,-8,-8,-14,-5,-14,-14,-6,-15,-5,-7,5,-11,14,-7,2,-9,0,-4,-1,-9,9,-10,-11,1,-4,-2,2,-9,-15,-12,-4,-8,-5,-11,-6,-4,-9,-4,-3,-7,4,9,-2,-5,-13,7,2,-5,-12,-14,1,13,-9,-3,-9,2,3,8,0,3}));
    }

}
