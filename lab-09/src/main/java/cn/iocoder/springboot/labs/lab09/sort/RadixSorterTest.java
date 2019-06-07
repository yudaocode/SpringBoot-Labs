package cn.iocoder.springboot.labs.lab09.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 基数排序
 *
 * 每个位的排序，通过桶排序实现，从而实现 k * O(n) 的复杂度
 */
public class RadixSorterTest {

    public static void main(String[] args) {
        int[] array = new int[]{
                23,
                50,
                100,
                1,
                10,
                21,
                50,
        };

        radixSort(array);
    }

    private static void radixSort(int[] array) {
        int digits = getMaxDigits(array);
        for (int digit = 0; digit < digits; digit++) {
            radixSort(array, digit);
        }

        System.out.println(Arrays.toString(array));
    }

    private static void radixSort(int[] array, int digit) {
        // 用于后面，获得第 digit 位
        int base = (int) Math.pow(10, digit);

        // 创建桶
        int bucketCounts = 10;
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(bucketCounts);
        for (int i = 0; i < bucketCounts; i++) {
            buckets.add(new ArrayList<>());
        }

        // 添加到桶中
        for (int value : array) {
            int bucketIndex = value / base % bucketCounts;
            buckets.get(bucketIndex).add(value);
        }

        // 顺序输出，自然有序
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (Integer value : bucket) {
                array[index++] = value;
            }
        }
    }

    private static int getMaxDigits(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        int digits = 0;
        while (max > 0) {
            digits++;
            max = max / 10;
        }
        return digits;
    }

}
