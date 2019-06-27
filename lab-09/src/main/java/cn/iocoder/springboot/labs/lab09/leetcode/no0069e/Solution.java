package cn.iocoder.springboot.labs.lab09.leetcode.no0069e;

public class Solution {

//    public int mySqrt(int x) {
//        // 0 和 1 ，不适合二分查找，因为我们的 low 和 high 的值。
//        if (x == 0 || x == 1) {
//            return x;
//        }
//
//        int low = 0;
//        int high = x;
//        while (true) {
//            int mid = ((high - low) / 2) + low;
//            int result = x / mid;
//            if (mid == result) {
//                return result;
//            }
//            if (mid == result + 1) {
//                return result;
//            }
//            if (result == mid + 1) {
//                return mid;
//            }
//            if (result > mid) {
//                low = mid + 1;
//            } else {
//                high = mid - 1;
//            }
//        }
//    }

    public int mySqrt(int x) { // 相比上面，更加简洁。
        // 0 和 1 ，不适合二分查找，因为我们的 low 和 high 的值。
        if (x == 0 || x == 1) {
            return x;
        }

        int low = 1;
        int high = x;
        int res = 0;
        while (low <= high) {
            int mid = ((high - low) / 2) + low;
            int result = x / mid;
            if (mid == result) {
                return result;
            }
            if (result > mid) {
                low = mid + 1;
                res = mid;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        new Solution().mySqrt(0);
//        System.out.println(new Solution().mySqrt(2147395599));
        for (int i = 0; i < 100; i++) {
            System.out.println(i + "：\t" + new Solution().mySqrt(i));
        }
    }

}
