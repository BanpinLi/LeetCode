public class Solution {
    public boolean isValidSudoku(char[][] board) {
        //一共进行27次判断，行、列、方格
        //进行行判断
        for(int i = 0;i < 9;i++) {
            int[] table = new int[10];
            for(int j = 0;j < 9;j++) {
                if(board[i][j] != '.') {
                    if(table[board[i][j] - '0'] > 0) {
                        return false;
                    } else {
                        table[board[i][j] - '0']++;
                    }
                }
            }
        }

        //进行列判断
        for(int j = 0;j < 9;j++) {
            int[] table = new int[10];
            for(int i = 0;i < 9;i++) {
                if(board[i][j] != '.') {
                    if(table[board[i][j] - '0'] > 0) {
                        return false;
                    } else {
                        table[board[i][j] - '0']++;
                    }
                }
            }
        }

        for(int i = 0;i < 9;i += 3) {
            for(int j = 0;j < 9;j += 3) {
                if(!isMatrixValid(board, i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    //传递进来一个row和col，判断一个3×3的矩阵是否是符合条件的
    private boolean isMatrixValid(char[][] chs, int row, int col) {
        int[] table = new int[10];
        for(int i = row;i < row + 3;i++) {
            for(int j = col;j < col + 3;j++) {
                if(chs[i][j] != '.') {
                    if(table[chs[i][j] - '0'] > 0) {
                        return false;
                    } else {
                        table[chs[i][j] - '0']++;
                    }
                }
            }
        }
        return true;
    }
}
