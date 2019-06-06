package cn.iocoder.springboot.labs.lab09.sort;

import java.util.Arrays;

/**
 * 合并排序
 */
public class MergeSorterTest {

    public static void main(String[] args) {
        {
            int[] array = new int[]{5, 4, 2, 3, 1, 2, 5, 2, 6};
            mergeSort(array);
        }
    }

    private static void mergeSort(int[] array) {
        int[] tmpArray = new int[array.length];
        mergeSort(array, tmpArray, 0, array.length - 1);

        System.out.println(Arrays.toString(array));
    }

    private static void mergeSort(int[] array, int[] tmpArray, int low, int high) {
        // 递归结束，如果 low 和 high 是相同的
        if (low >= high) {
            return;
        }

        // 折半，进行分解
        int middle = low + (high - low >> 1); // >> 1 等价于 / 2
        mergeSort(array, tmpArray, low, middle);
        mergeSort(array, tmpArray, middle + 1, high);

        // 合并
        merge(array, tmpArray, low, middle, high);
    }

    private static void merge(int[] array, int[] tmpArray, int low, int middle, int high) {
        // 比较排序
        int highStart = middle + 1;
        int lowStart = low; // 需要赋值出来，是为了避免 low 被修改
        int tmpArrayIndex = low; // tmpArray 的 index
        while (lowStart <= middle && highStart <= high) { // 能够这么操作的原因是，每次递归，最终的结果都是有序的。所以就一直可以有序了。
            if (array[lowStart] > array[highStart]) {
                tmpArray[tmpArrayIndex++] = array[highStart++];
            } else {
                tmpArray[tmpArrayIndex++] = array[lowStart++];
            }
        }

        // 将上述未赋值到 tmpArray 的，继续赋值进去
        while (lowStart <= middle) {
            tmpArray[tmpArrayIndex++] = array[lowStart++];
        }
        while (highStart <= high) {
            tmpArray[tmpArrayIndex++] = array[highStart++];
        }

        // 将排序后的 tmpArrayIndex 赋值到 array 中，从 low 开始到 high 部分。
        System.arraycopy(tmpArray, low, array, low, high - low + 1);
    }

}
