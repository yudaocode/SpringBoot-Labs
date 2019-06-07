package cn.iocoder.springboot.labs.lab09.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSorterTest {

    public static void main(String[] args) {
        {
            int[] array = new int[]{5, 4, 2, 3, 1, 2, 5, 2, 6};
//            int[] array = new int[]{6, 11, 8};
            quickSort(array);
        }
    }

    private static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);

        System.out.println(Arrays.toString(array));
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }

        // 寻找 partition 位置
//        int partitionIndex = partition(array, low, high);
        int partitionIndex = partition02(array, low, high);

        // 递归排序
        quickSort(array, low, partitionIndex - 1);
        quickSort(array, partitionIndex + 1, high);
    }

    @SuppressWarnings("Duplicates")
    public static int partition(int[] array, int low, int high) {
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

    // partition 的逻辑，基于 swap ，需要 3 次操作
    // partition02 的逻辑，基于赋值来做，只需要 1 次操作。相比来说，性能更好。
    // 当然，第一次看 partition02 的逻辑，可能会有点懵逼。大体的理解是，low 和 high 之间，被不断夹紧，把比 pivot 大的放到右侧，比 pivot 小的放到左侧。
    // 本质可以理解成:
    //  1. pivot = arr[low]; =》 A -> B
    //  2. arr[low] = arr[high]; =》 B -> C
    //  3. arr[high] = arr[low]; =》 C -> D
    //  4. 重复循环，我们最终找到真正的 D ，将 原 B ，也就是 A ，复制上去。
    private static int partition02(int[] arr, int low, int high) {
        int pivot = arr[low];     //枢轴记录
        // 循环，达到 arr[low] 左边是比它小，右边是比它大。
        while (low < high) { // 实际上，最终 break 的条件是，low 和 high 相等。具体可以看里面的两个 while
            while (low < high && arr[high] >= pivot) --high; // 因为 high 原来就在右侧，所以不需要移动
            arr[low] = arr[high];             // 交换比枢轴小的记录到左端

            while (low < high && arr[low] <= pivot) ++low; // 因为 low 原来就在左侧，所以不需要移动
            arr[high] = arr[low];           // 交换比枢轴小的记录到右端
        }
        //扫描完成，枢轴到位
        arr[low] = pivot;
        //返回的是枢轴的位置
        return low;
    }

}
