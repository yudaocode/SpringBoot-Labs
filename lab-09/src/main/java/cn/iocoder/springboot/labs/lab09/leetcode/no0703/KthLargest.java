package cn.iocoder.springboot.labs.lab09.leetcode.no0703;

class KthLargest {

    /**
     * 小顶堆
     */
    private int[] heap;
    /**
     * 堆的位置
     */
    private int heapIndex;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.heap = new int[k + 1];
        this.heapIndex = 1;
        this.k = k;
        // 初始化
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (heapIndex <= k) {
            // 赋值
            heap[heapIndex] = val;
            heapIndex++;

            // 向上堆化
            heapifyUp(heapIndex - 1);
        } else {
            if (val > heap[1]) { // 大于最小值，才有资格加入其中。因为我们构建的 heap 是小顶堆，最上面存储的是第 k 大。
                // 赋值
                heap[1] = val;

                heapifyDown(1);
            }
        }

        return heap[1];
    }

//    private int remove0() {
//        // 取头元素
//        int val = heap[1];
//
//        // 将尾巴设置到头
//        heap[1] = heap[heapIndex - 1];
//        heapIndex--;
//
//        // 向下堆化
//        heapifyDown(1);
//
//        return val;
//    }

    public void heapifyUp(int index) {
        while (index > 1) { // 注意，此处要大于 1
            int parent = index / 2;
            if (heap[index] < heap[parent]) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        while (true) {
            int pos = index;
            // 求子节点中，哪个
            if (index * 2 < heapIndex && heap[index * 2] < heap[index]) {
                pos = index * 2;
            }
            if (index * 2 + 1 < heapIndex && heap[index * 2 + 1] < heap[pos]) {
                pos = index * 2 + 1;
            }
            // 如果毫无变化，说明已经不需要继续向下
            if (pos == index) {
                return;
            }
            // 交换
            swap(index, pos);
            index = pos;
        }
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

//    private int findMaxValueIndex() {
//        int max = Integer.MIN_VALUE;
//        int maxIndex = -1;
//        for (int i = log2(heapIndex) * 2; i < heapIndex; i++) { // 从 log2(heapIndex) * 2 的原因是，从叶子节点开始。因为我们构建的是小顶堆，那么最大值必定在叶子节点上。
//            if (heap[i] > max) {
//                max = heap[i];
//                maxIndex = i;
//            }
//        }
//        return maxIndex;
//    }
//
//    private static int log2(int x) {
//        return (int) (Math.log(x) / Math.log(2));
//    }

    public static void main(String[] args) {
        if (false) {
            int k = 3;
            int[] nums = {4, 5, 8, 2};
            KthLargest kthLargest = new KthLargest(k, nums);
            System.out.println(kthLargest.add(3));
            System.out.println(kthLargest.add(5));
            System.out.println(kthLargest.add(10));
            System.out.println(kthLargest.add(9));
            System.out.println(kthLargest.add(4));
        }
        if (true) {
            int k = 1;
            int[] nums = {};
            KthLargest kthLargest = new KthLargest(k, nums);
            System.out.println(kthLargest.add(-3));
            System.out.println(kthLargest.add(-2));
            System.out.println(kthLargest.add(-4));
            System.out.println(kthLargest.add(0));
            System.out.println(kthLargest.add(4));
        }
    }

}
