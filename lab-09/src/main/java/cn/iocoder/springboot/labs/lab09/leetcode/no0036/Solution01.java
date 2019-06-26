package cn.iocoder.springboot.labs.lab09.leetcode.no0036;

public class Solution01 {

    private void reset(boolean[] flags) {
        for (int i = 0; i < flags.length; i++) {
            flags[i] = false;
        }
    }

    public boolean isValidSudoku(char[][] board) {
        int n = 9;
        int m = 3;
        boolean[] flags = new boolean[n];

        // 横线
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (flags[board[i][j ] - '1']) {
                    return false;
                }
                flags[board[i][j ] - '1'] = true;
            }
            reset(flags);
        }

        // 竖线
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[j][i] == '.') {
                    continue;
                }
                if (flags[board[j][i ] - '1']) {
                    return false;
                }
                flags[board[j][i ] - '1'] = true;
            }
            reset(flags);
        }

        // 每个方块
        for (int i = 0; i < n; i = i + m) {
            for (int j = 0; j < n; j = j + m) {
                for (int p = i; p < i + m; p++) {
                    for (int q = j; q < j + m; q++) {
                        if (board[p][q] == '.') {
                            continue;
                        }
                        if (flags[board[p][q] - '1']) {
                            return false;
                        }
                        flags[board[p][q] - '1'] = true;
                    }
                }
                reset(flags);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] board = {{'.','.','.','.','5','.','.','1','.'},{'.','4','.','3','.','.','.','.','.'},{'.','.','.','.','.','3','.','.','1'},{'8','.','.','.','.','.','.','2','.'},{'.','.','2','.','7','.','.','.','.'},{'.','1','5','.','.','.','.','.','.'},{'.','.','.','.','.','2','.','.','.'},{'.','2','.','9','.','.','.','.','.'},{'.','.','4','.','.','.','.','.','.'}};
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                System.out.print(board[i][j] + "\t");
//            }
//            System.out.println();
//        }
        System.out.println(new Solution01().isValidSudoku(board));
    }

}
