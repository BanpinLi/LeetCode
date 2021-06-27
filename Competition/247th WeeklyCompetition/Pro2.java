public class Pro2 {
    public int[][] rotateGrid(int[][] grid, int k) {
        //由于k可能会很大，所以需要考虑到周期性
        int m = grid.length;
        int n = grid[0].length;
        int row = 0;
        int col = 0;
        while(m > 0 && n > 0) {
            rotate(grid, row, col, m, n, k);
            m -= 2;
            n -= 2;
            row++;
            col++;
        }
        return grid;
    }

    //表示的是从row和col开始操作，行为m，列为n，旋转n次
    private void rotate(int[][] nums, int row, int col, int m, int n, int k) {
        //由于k可能会很大，所以进行此操作一次
        int len = (2 * n + (m - 2) * 2);
        k = k % len;
        //先将顺序数据加载到数组里面
        int[] temp = new int[len];
        int index = 0;
        for(int i = row;i < row + m;i++) {
            temp[index++] = nums[i][col];
        }
        for(int i = col + 1;i < col + n;i++) {
            temp[index++] = nums[row + m - 1][i];
        }
        for(int i = row + m - 2;i >= row;i--) {
            temp[index++] = nums[i][col + n - 1];
        }
        for(int i = col + n - 2;i > col;i--) {
            temp[index++] = nums[row][i];
        }

        //将数据移动k位
        int[] ansData = new int[len];
        for(int i = k;i < len;i++) {
            ansData[i] = temp[i - k];
        }
        for(int i = 0;i < k;i++) {
            ansData[i] = temp[len - k + i];
        }

        temp = ansData;
        index = 0;
        for(int i = row;i < row + m;i++) {
            nums[i][col] = temp[index++];
        }
        for(int i = col + 1;i < col + n;i++) {
            nums[row + m - 1][i] = temp[index++];
        }
        for(int i = row + m - 2;i >= row;i--) {
            nums[i][col + n - 1] = temp[index++];
        }
        for(int i = col + n - 2;i > col;i--) {
            nums[row][i] = temp[index++];
        }
    }
}
