package cn.iocoder.springboot.labs.lab09.leetcode.no0300;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分 + 贪心
 */
public class Solution02 {

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        List<Integer> lis = new ArrayList<>(); // 上升序列
        for (int num : nums) {
            int position = binarySearch(lis, num);
            if (position >= lis.size()) {
                lis.add(num);
            } else {
                lis.set(position, num);
            }
        }


        return lis.size();
    }

    /**
     * 获得比 num 第一大的值的位置
     * 如果有和 num 相等的，也返回
     *
     * TODO 芋艿，后续，可以使用 Arrays.binarySearch() 简化。
     *
     * @param lis
     * @param num
     * @return
     */
    private int binarySearch(List<Integer> lis, int num) {
        int high = lis.size() - 1;

        // 判断，是否超过
        if (high < 0 || lis.get(high) < num) {
            return lis.size();
        }
        if (num < lis.get(0)) {
            return 0;
        }

        // 二分查找
        int low = 0;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (lis.get(mid) == num) {
                return mid;
            } else if (num < lis.get(mid)) {
                if (lis.get(mid - 1) > num) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                if (lis.get(mid + 1) > num) {
                    return mid + 1;
                } else {
                    low = mid + 1;
                }
            }
        }

        throw new IllegalArgumentException("不可能出现");
    }

    public static void main(String[] args) {
        Solution02 solution = new Solution02();
//        System.out.println(solution.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(solution.lengthOfLIS(new int[]{7,8,9,10,1,2,3,4,5,5}));
    }

}
