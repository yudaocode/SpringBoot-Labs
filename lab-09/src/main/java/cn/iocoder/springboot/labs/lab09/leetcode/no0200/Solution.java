package cn.iocoder.springboot.labs.lab09.leetcode.no0200;

public class Solution {

    public static class UnionFind {

        /**
         * 每个岛的节点的指向哪个岛
         */
        private int[] roots;
        /**
         * 岛的数量
         */
        private int count;

        public UnionFind(char[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            roots = new int[n * m];
            // 初始，每个小岛都指向自己
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] != '1') {
                        continue;
                    }
                    roots[getIndex(grid, i, j)] = getIndex(grid, i, j);
                    count++;
                }
            }
        }

        public int findRoot(int i) {
            int root = i;
            // 寻找真正的 root
            while (root != roots[root]) { // 自己指向自己的，才是真正的 root
                root = roots[root];
            }

            // 路径压缩
            while (i != root) { // 不断向上
                int tmp = roots[i];
                roots[i] = root;
                i = tmp;
            }

            return root;
        }

        /**
         * 将两个小岛进行合并
         *
         * @param p 岛 1
         * @param q 岛 2
         */
        public void union(int p, int q) {
            int pRoot = findRoot(p);
            int qRoot = findRoot(q);
            // 如果 root 不同，则进行关联
            if (pRoot != qRoot) {
                roots[pRoot] = qRoot;
                count--;
            }
        }

    }

    private static int[][] directions = {{1, 0}, {0, 1}};

    private static boolean isValid(char[][] grid, int i, int j) {
        return i >= 0 && i < grid.length
                && j >=0 && j < grid[i].length
                && grid[i][j] == '1';
    }

    private static int getIndex(char[][] grid, int i, int j) {
//        int n = grid.length;
        int m = grid[0].length;
        return i * m + j;
    }

    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // 创建并查级
        UnionFind unionFind = new UnionFind(grid);

        // 开始合并
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 非岛屿，跳过
                if (!isValid(grid, i, j)) {
                    continue;
                }

                // 判断是否目标是岛屿
                for (int[] direction : directions) {
                    // 非岛屿，跳过
                    if (!isValid(grid, i + direction[0], j + direction[1])) {
                        continue;
                    }

                    unionFind.union(getIndex(grid, i, j),
                            getIndex(grid, i + direction[0], j + direction[1]));
                }

            }
        }

        return unionFind.count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.numIslands(new char[][]{
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'},
//        }));
//        System.out.println(solution.numIslands(new char[][]{
//                {'1', '1', '0', '0', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '1', '0', '0'},
//                {'0', '0', '0', '1', '1'},
//        }));
        System.out.println(solution.numIslands(new char[][]{
                {'1'},
                {'1'}
        }));
    }

}
