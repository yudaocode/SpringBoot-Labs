package cn.iocoder.springboot.labs.lab09.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BucketSorterTest {

    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[1000];
        for (int i = 0; i < 1000; i++) {
            array[i] = random.nextInt(10000);
        }
        bucketSort(array, 100);
    }

    // bucketSize - 指的是每个 bucket 的大小
    public static void bucketSort(int[] array, int bucketSize) {
        // 求最大最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int value : array) {
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }

        // 创建桶
        int bucketCounts = (max - min) / bucketSize + 1; // 这里后面要优化下，向上取整。例如说 101 / 10 ，理论来说，应该是 11 个木桶
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(bucketCounts); // 使用 ArrayList ，方便实现，减少扩容等等麻烦
        for (int i = 0; i < bucketCounts; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        // 添加到桶中
        for (int value : array) {
            buckets.get((value - min) / bucketSize).add(value); // -min ，因为它是起点
        }

        // 排序每个桶
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket); // 方便
        }
        System.out.println(buckets);

        // 打印结果
    }

}
