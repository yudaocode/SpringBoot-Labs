package cn.iocoder.springboot.labs.lab09.search;

import java.util.Random;

/**
 * 参考 {@link SkipList} ，自己实现一遍跳表。
 *
 * 通过 {@link Node} 的 forwards 数组实现，确实挺巧妙，代码量也非常精简。
 *
 * 目前，网络上找到的 Java 跳表实现，主要是 https://sylvanassun.github.io/2017/12/31/2017-12-31-skip_list/ 这种。实现方式略有差别。
 *
 * @author yunai
 */
@SuppressWarnings("Duplicates")
public class SkipList2 {

    private static final int MAX_LEVEL = 16;

    private Random random = new Random();

    /**
     * 总层级数
     */
    private int levelCount = 1;

    private Node head = new Node(null, MAX_LEVEL);

    public Node find(int value) {
        // 自最上层索引，开始往下查询
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) { // 这里看的会有点绕，第一次 for ，跳转到对应 i 层，后续的，就是第 i 层的不断向下指向
                p = p.forwards[i];
            }
        }

        // 判断是否相等
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        }
        return null;
    }

    public void insert(int value) {
        // 创建 Node
        int maxLevel = randomLevel();
        Node node = new Node(value, maxLevel);

        // 寻找每一层的指向
        Node[] update = new Node[maxLevel];
//        for (int i = 0; i < maxLevel; i++) { // 初始化 node 在一层的指向 。不需要，因为下面的 for 循环，一定会给 update 数组赋值。
//            update[i] = head;
//        }
        Node p = head;
        for (int i = maxLevel - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) { // 这里看的会有点绕，第一次 for ，跳转到对应 i 层，后续的，就是第 i 层的不断向下指向
                p = p.forwards[i];
            }
            update[i] = p;
        }

        // 设置指向
        for (int i = 0; i < maxLevel; i++) {
            // 设置 node 在第 i 层，指向 update[i].forwards[i]
            node.forwards[i] = update[i].forwards[i];
            // 将 update[i].forwards[i] 赋值成 node 。
            // 这样，就形成了 update[i].forwards[i] = node ，并且 node..forwards[i] = 原 update[i].forwards[i] 。可能有点绕，可以调试下
            update[i].forwards[i] = node;
        }

        // 设置新的最高高度
        if (maxLevel > levelCount) {
            levelCount = maxLevel;
        }
    }

    public void delete(int value) {
        // 寻找一层对 value 应该 Node 的指向
        Node[] update = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) { // 这里看的会有点绕，第一次 for ，跳转到对应 i 层，后续的，就是第 i 层的不断向下指向
                p = p.forwards[i];
            }
            update[i] = p;
        }

        // 如果找到指定节点
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; i--) {
                // 指定层，有符合 value 应该 Node 的指向，进行删除
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }
    }

    private int randomLevel() {
        return random.nextInt(MAX_LEVEL - 1) + 1; // 一定建立索引，避免直接添加到第 0 层。不然可能会丢失。
//         return random.nextInt(MAX_LEVEL); // 因为我们是从 1 层开始计数，可以看 levelCount 参数
    }

//    // 随机 level 次，如果是奇数层数 +1，防止伪随机
//    private int randomLevel() {
//        int level = 1;
//        for (int i = 1; i < MAX_LEVEL; ++i) {
//            if (random.nextInt() % 2 == 1) {
//                level++;
//            }
//        }
//
//        return level;
//    }

    /**
     * 节点
     *
     * 通过 {@link #forwards} 属性，记录在每一层的指向。
     *
     * 注意，相同 {@link #data} ，在多层，通过 {@link #forwards} 属性来实现，而不是指向。
     */
    public class Node {

        // TODO 芋艿，如果想实现类似 HashMap 的功能，可以把 data 改成 key + value 。
        /**
         * 数值
         */
        private Integer data;

        /**
         * 最高层级
         */
        private int maxLevel;
        /**
         * 对指定层级的指向
         */
        private Node[] forwards;

        public Node(Integer data, int maxLevel) {
            this.data = data;
            this.maxLevel = maxLevel;
            this.forwards = new Node[maxLevel];
//            this.forwards = new Node[MAX_LEVEL];
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", maxLevel=" + maxLevel +
                    '}';
        }
    }

}
