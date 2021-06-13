public class Pro3 {
    public int largestMagicSquare(int[][] grid) {
        //使用遍历的解法，从高阶到低阶，一旦得到的矩阵是满足条件的矩阵，那么直接返回这个矩阵的宽度
        int ans = Math.min(grid.length, grid[0].length);
        for(int k = ans;k >= 0;k--) {
            for(int i = 0;i + k <= grid.length;i++) {
                for(int j = 0;j + k <= grid[0].length;j++) {
                    if(isMagicSquare(grid, i, j, k)) {
                        return k;
                    }
                }
            }
        }
        return 0;
    }

    private boolean isMagicSquare(int[][] nums, int row, int col, int k) {
        int target = 0;
        for(int i = row;i < row + k;i++) {
            target += nums[i][col];
        }
        //检查列
        for(int j = col;j < col + k;j++) {
            int sum = 0;
            for(int i = row;i < row + k;i++) {
                sum += nums[i][j];
            }
            if(sum != target) {
                return false;
            }
        }
        //检查行
        for(int i = row;i < row + k;i++) {
            int sum = 0;
            for(int j = col;j < col + k;j++) {
                sum += nums[i][j];
            }
            if(sum != target) {
                return false;
            }
        }
        //检查对角线
        int sum = 0;
        for(int i = row, j = col;i < row + k;i++, j++) {
            sum += nums[i][j];
        }
        if(sum != target) {
            return false;
        }
        sum = 0;
        for(int i = row, j = col + k - 1;i < row + k;i++, j--) {
            sum += nums[i][j];
        }
        if(sum != target) {
            return false;
        }
        return true;
    }
}
