package src;

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 左下角到右上角进行搜索
        // 左下角的元素满足几个性质
        // 同一行右边元素全比他大
        // 同一列上面元素全比他小
        // 根据这个性质，每次向上或者向右进行移动就可以了
        // 从右上角(左上角)看过去，类似于一颗BST
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] < target) {
                col++;
            } else if (matrix[row][col] > target) {
                row--;
            } else {
                return true;
            }
        }
        return false;
    }
}
