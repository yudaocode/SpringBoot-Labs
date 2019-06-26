package cn.iocoder.springboot.labs.lab09.leetcode.no0037;

@SuppressWarnings("Duplicates")
public class Solution01 {

    public void solveSudoku(char[][] board) {
        int n = 9;
        int m = 3;
        boolean[][] rows = new boolean[n][n];
        boolean[][] cols = new boolean[n][n];
        boolean[][] boxes = new boolean[n][n];

        init(board, n, m,
                rows, cols, boxes);

        boolean result = solveSudoku(board, n, m, 0, 0,
                rows, cols, boxes);
        System.out.println(result);
    }

    private void init(char[][] board, int n, int m,
                      boolean[][] rows, boolean[][] cols, boolean[][] boxes) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int number = board[i][j] - '1'; // 减 1 而不是 0 ，因为数组从 0 开始。
                // 行
                rows[i][number] = true;
                // 列
                cols[j][number] = true;
                // 小方格
                int boxIndex = i / m * m + j / m;
                boxes[boxIndex][number] = true;
            }
        }
    }

    private boolean solveSudoku(char[][] board, int n, int m, int i, int j,
                                boolean[][] rows, boolean[][] cols, boolean[][] boxes) {
        if (i == n && j == 0) {
            return true;
        }

        // 如果已经填写，判断有效性
        if (board[i][j] != '.') {
            // 递归
            return solveSudoku(board, n, m, j + 1 == n ? i + 1 : i, j + 1 == n ? 0 : j + 1,
                    rows, cols, boxes);
        }

        // 如果未填写，就开始模拟填写
        for (int number = 0; number < n; number++) {
            // 行
            if (rows[i][number]) {
                continue;
            }
            // 列
            if (cols[j][number]) {
                continue;
            }
            // 小方格
            int boxIndex = i / m * m + j / m;
            if (boxes[boxIndex][number]) {
                continue;
            }
            // 统一设置
            rows[i][number] = true;
            cols[j][number] = true;
            boxes[boxIndex][number] = true;
            board[i][j] = (char) (number + '1');
            // 递归
            boolean success = solveSudoku(board, n, m, j + 1 == n ? i + 1 : i, j + 1 == n ? 0 : j + 1,
                    rows, cols, boxes);
            // 成功
            if (success) {
                return true;
            }
            // 失败
            rows[i][number] = false;
            cols[j][number] = false;
            boxes[boxIndex][number] = false;
            board[i][j] = '.';
        }

        // 如果一直失败，说明就是失败了
        return false;
    }

    public static void main(String[] args) {
        if (false) {
            char[][] board = {{'.', '.', '.', '.', '5', '.', '.', '1', '.'}, {'.', '4', '.', '3', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '3', '.', '.', '1'}, {'8', '.', '.', '.', '.', '.', '.', '2', '.'}, {'.', '.', '2', '.', '7', '.', '.', '.', '.'}, {'.', '1', '5', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '2', '.', '.', '.'}, {'.', '2', '.', '9', '.', '.', '.', '.', '.'}, {'.', '.', '4', '.', '.', '.', '.', '.', '.'}};
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                System.out.print(board[i][j] + "\t");
//            }
//            System.out.println();
//        }
            new Solution01().solveSudoku(board);
        }
        if (true) {
            char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
            new Solution01().solveSudoku(board);
        }
    }

}
