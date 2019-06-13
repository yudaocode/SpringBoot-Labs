package cn.iocoder.springboot.labs.lab09.sort;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSorterTest {

    public static void main(String[] args) {
        if (false) {
            HeapSorter heapSorter = new HeapSorter(10);
            heapSorter.insert(10);
            heapSorter.insert(3);
            heapSorter.insert(5);
            heapSorter.insert(1);
            heapSorter.insert(2);
            heapSorter.insert(20);
            System.out.println(Arrays.toString(heapSorter.heap));

            heapSorter.remove(2);
            System.out.println(Arrays.toString(heapSorter.heap));
        }

        if (true) {
            int[] array = {0, 10, 3, 5, 1, 2, 20};
            HeapSorter heapSorter = HeapSorter.create(array, array.length - 1);
//            System.out.println(Arrays.toString(heapSorter.heap));
            heapSorter.sort();
        }
    }

    public static class HeapSorter {

        /**
         * 堆
         */
        private int[] heap;
        /**
         * 容量
         */
        private int capacity;
        /**
         * 元素数量
         */
        private int count;

        public HeapSorter(int[] heap, int count) {
            this.capacity = heap.length;
            this.heap = heap;
            this.count = count;
        }

        public HeapSorter(int capacity) {
            this.capacity = capacity;
            this.heap = new int[capacity + 1]; // 因为 0 被占用了
            count = 0;
        }

        public void insert(int value) {
            if (count >= capacity) {
                throw new IllegalStateException("容量已满");
            }
            ++count;
            heap[count] = value;

            // 自下向上堆化，如果大于父节点
            int index = count;
            while (index >> 1 > 0 && heap[index] > heap[index >> 1]) {
                swap(index, index >> 1);
                index = index >> 1;
            }
        }

        public int remove(int pos) {
            if (count == 0) {
                throw new IllegalStateException("不存在最大值");
            }
            int tmp = heap[pos];
            heap[pos] = heap[count];
            heap[count] = 0; // 置空，其实非必要。就是为了好看
            count--;

            heapify(heap, count, pos);
            return tmp;
        }

        /**
         * 排序
         */
        public void sort() {
            while (count > 1) {
                System.out.println(remove(1));
            }
        }

        private void swap(int i, int j) {
            swap(heap, i, j);
        }

        public static HeapSorter create(int[] heap, int count) {
            for (int i = count / 2; i >= 1; i--) {
                heapify(heap, count, i);
            }
            return new HeapSorter(heap, count);
        }

        private static void heapify(int[] heap, int count, int pos) {
            // 自伤向下，
            while (true) {
                int maxPos = pos;
                if (pos * 2 <= count && heap[pos] < heap[pos * 2]) {
                    maxPos = pos * 2;
                }
                if (pos * 2 + 1 <= count && heap[maxPos] < heap[pos * 2 + 1]) {
                    maxPos = pos * 2 + 1;
                }
                // 判断相等，说明没变化
                if (maxPos == pos) {
                    return;
                }
                swap(heap, pos, maxPos);
            }
        }

        private static void swap(int[] heap, int i, int j) {
            int tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }

    }

}
