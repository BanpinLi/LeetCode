class Solution_Optimize {
    //代码速度并没有什么明显提高，只是将代码的书写复杂度降低了
    public boolean isValidSudoku(char[][] board) {
        //使用三个二维数组来记录三种不同情况下的数字出现情况，当出现第二次的时候，直接返回false
        boolean[][] rowTable = new boolean[9][9];
        boolean[][] colTable = new boolean[9][9];
        boolean[][] matrixTable = new boolean[9][9];

        for(int i = 0;i < 9;i++) {
            for(int j = 0;j < 9;j++) {
                if(board[i][j] != '.') {
                    int blockIndex = i / 3 * 3 + j / 3;     //一共有9个区块，将这9块分别对应于9行
                    int num = board[i][j] - '1';
                    if(rowTable[i][num] || colTable[j][num] || matrixTable[blockIndex][num]) {
                        return false;
                    } else {
                        rowTable[i][num] = true;
                        colTable[j][num] = true;
                        matrixTable[blockIndex][num] = true;
                    }
                }
            }
        }

        return true;
    }
}