package cn.iocoder.springboot.labs.lab09.leetcode.no0547;

@SuppressWarnings("Duplicates")
public class Solution02 {

    public class UnionFind {

        /**
         * 指向
         */
        private int[] roots;
        /**
         * 数量
         */
        private int count;

        public UnionFind(int[][] M) {
            this.count = M.length;
            this.roots = new int[count];
            for (int i = 0; i < count; i++) {
                roots[i] = i;
            }
        }

        public int findRoot(int i) {
            int root = i;
            // 寻找真正的 root
            while (roots[root] != root) {
                root = roots[root];
            }

            // 如果自己不是 root ，需要将所有父节点，改成 root
            while (i != root) {
                int tmp = roots[i];
                roots[i] = root;
                i = tmp;
            }

            return root;
        }

        // 这个写法，是上面的写法的省略
//        public int findRoot(int i) {
//            // 路径压缩，并修改 i 为 root 。
//            while (i != roots[i]) {
//                roots[i] = roots[roots[i]];
//                i = roots[i];
//            }
//
//            return i;
//        }

        public void union(int p, int q) {
            int pRoot = findRoot(p);
            int qRoot = findRoot(q);

            if (pRoot != qRoot) {
                roots[qRoot] = pRoot;
                count--;
            }
        }

    }

    public int findCircleNum(int[][] M) {
        int n = M.length;
        if (n == 0) {
            return 0;
        }

        UnionFind unionFind = new UnionFind(M);

        // 重新梳理指向
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }

//        for (int i = 1; i < n; i++) {
//            for (int j = 1; j < n; j++) {
//                if (M[i][j] == 1) {
//                    unionFind.union(i, j);
//                }
//            }
//        }

        return unionFind.count;
    }

    public static void main(String[] args) {
        Solution02 solution = new Solution02();
//        System.out.println(solution.findCircleNum(new int[][]{
//                {1,1,0},
//                {1,1,0},
//                {0,0,1}
//        }));
//        System.out.println(solution.findCircleNum(new int[][]{
//                {1,1,0},
//                {1,1,1},
//                {0,1,1}
//        }));
//        System.out.println(solution.findCircleNum(new int[][]{
//                {1,0,0,1},
//                {0,1,1,0},
//                {0,1,1,1},
//                {1,0,1,1}
//        }));

        System.out.println(solution.findCircleNum(new int[][]{
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1},
        }));

//        System.out.println(solution.findCircleNum(new int[][]{
//                {1,1,1,1, 1},
//                {1,1,1,1, 1},
//                {1,1,1,1, 1},
//                {1,1,1,1, 1},
//                {1,1,1,1, 1},
//        }));

//        System.out.println(solution.findCircleNum(new int[][]{{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},{0,1,0,1,0,0,0,0,0,0,0,0,0,1,0},{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{0,1,0,1,0,0,0,1,0,0,0,1,0,0,0},{0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,0,1,1,0,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0},{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},{0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},{0,1,0,0,0,0,0,0,0,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}}));
//        System.out.println(solution.findCircleNum(new int[][]{{1,1,0,0,0,0,0,1,0,0,0,0,0,0,0},{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,1,1,0,0,0,0,0,0,0,0},{0,0,0,0,1,0,0,0,0,1,1,0,0,0,0},{0,0,0,1,0,1,0,0,0,0,1,0,0,0,0},{0,0,0,1,0,0,1,0,1,0,0,0,0,1,0},{1,0,0,0,0,0,0,1,1,0,0,0,0,0,0},{0,0,0,0,0,0,1,1,1,0,0,0,0,1,0},{0,0,0,0,1,0,0,0,0,1,0,1,0,0,1},{0,0,0,0,1,1,0,0,0,0,1,1,0,0,0},{0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,1,0,1,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,0,1,0,0,0,0,1}}));
    }

}
