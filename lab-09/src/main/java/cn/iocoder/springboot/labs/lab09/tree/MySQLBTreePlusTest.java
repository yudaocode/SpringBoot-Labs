package cn.iocoder.springboot.labs.lab09.tree;

/**
 * MySQL B+Tree
 */
public class MySQLBTreePlusTest {

    /**
     * 非叶子节点
     */
    public static class Node {

        /**
         * 5 叉树
         */
        public static int m = 5;

        /**
         * 键值的数组
         *
         * m - 1 的原因是，keywords 代表的是区间。
         *
         * children[0] 的范围是 [ -无穷, keywords[0] ]
         * children[1] 的泛微是
         */
        private int[] keywords = new int[m - 1];

        /**
         * 保存子节点的指针
         */
        private Node[] children = new Node[m];

    }

    /**
     * 叶子节点
     */
    public static class LeafNode {

        /**
         * 假设每个叶子节点存储三个数据行的键值和数据地址信息
         */
        public static int k = 3;

        /**
         * 数据行的键值
         *
         * 不同于 {@link Node#keywords} ，这里表示的是具体值
         */
        private int[] keywords = new int[k];
        /**
         * 数据航的地址值
         */
        private int[] dataAddresses = new int[k];

        /**
         * 前置的叶子节点，用于区间检索
         */
        private LeafNode prev;
        /**
         * 后置的叶子节点，用于区间检索
         */
        private LeafNode next;

    }

}
