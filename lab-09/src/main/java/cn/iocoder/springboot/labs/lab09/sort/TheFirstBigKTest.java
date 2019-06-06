package cn.iocoder.springboot.labs.lab09.sort;

import java.util.Arrays;

/**
 * 基于快速排序的方式，实现第 k 大的数
 */
public class TheFirstBigKTest {

    public static void main(String[] args) {
        {
            int[] array = new int[]{5, 4, 2, 3, 1, 2, 5, 2, 6};
//            int[] array = new int[]{6, 11, 8};
//            quickSort(array, 1);
//            quickSort(array, 2);
//            quickSort(array, 3);
//            quickSort(array, 4);
            quickSort(array, 5);
        }
    }

    private static void quickSort(int[] array, int k) {
        if (k > array.length) {
            throw new IllegalStateException("k 不能大于最大数组");
        }
        quickSort(array, k - 1, 0, array.length - 1);

        System.out.println(Arrays.toString(array));
        System.out.println("答案 k ：" + array[k - 1]);
    }

    private static void quickSort(int[] array, int k, int low, int high) {
        if (low >= high) {
            return;
        }

        int partition = partition(array, low, high);
        if (partition == k) {
            return;
        }

        if (partition < k) {
            quickSort(array, k, partition + 1, high);
        } else {
            quickSort(array, k, low, partition - 1);
        }
    }

    @SuppressWarnings("Duplicates")
    private static int partition(int[] array, int low, int high) {
        int partitionValue = array[high]; // 首先，选择 high 位置的值，作为 partitionValue 值
        int pos = low;
        // 遍历 array 的 low 到 high 区间，将小于 partitionValue 值的部分，顺序放到 [0, pos) 范围内。最终，pos 我们会放 partitionValue 值。
        for (int i = low; i < high; i++) {
            if (array[i] < partitionValue) {
                // 进行交换
                swap(array, i, pos);
                // pos + 1 ，用于下一个使用
                pos++;
            }
        }

        // 将 pos 我们会放 partitionValue 值。
        swap(array, pos, high);

        // 返回 partitionValue 所在位置
        return pos;
    }

    private static void swap(int[] array, int pos1, int pos2) {
        if (pos1 == pos2) {
            return;
        }
        int tmp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = tmp;
    }

}
