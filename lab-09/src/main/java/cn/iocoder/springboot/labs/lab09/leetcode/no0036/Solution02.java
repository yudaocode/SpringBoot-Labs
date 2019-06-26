package cn.iocoder.springboot.labs.lab09.leetcode.no0036;

public class Solution02 {

    private void reset(boolean[] flags) {
        for (int i = 0; i < flags.length; i++) {
            flags[i] = false;
        }
    }

    public boolean isValidSudoku(char[][] board) {
        int n = 9;
        int m = 3;
//        boolean[] flags = new boolean[n];
        boolean[][] rows = new boolean[n][n];
        boolean[][] cols = new boolean[n][n];
        boolean[][] boxes = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int number = board[i][j] - '1'; // 减 1 而不是 0 ，因为数组从 0 开始。
                // 行
                if (rows[i][number]) {
                    return false;
                }
                rows[i][number] = true;
                // 列
                if (cols[j][number]) {
                    return false;
                }
                cols[j][number] = true;
                // 小方格
                int boxIndex = i / m * m + j / m;
                if (boxes[boxIndex][number]) {
                    return false;
                }
                boxes[boxIndex][number] = true;
            }
        }

        return true;
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
            System.out.println(new Solution02().isValidSudoku(board));
        }
        if (true) {
            char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
            System.out.println(new Solution02().isValidSudoku(board));
        }
    }

}
