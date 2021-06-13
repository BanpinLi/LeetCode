public class Pro1 {
    public boolean findRotation(int[][] mat, int[][] target) {
        for(int count = 0;count < 4;count++) {
            mat = reverse(mat);
            boolean flag = false;
            for(int i = 0;i < mat.length;i++) {
                for(int j = 0;j < mat.length;j++) {
                    if(mat[i][j] != target[i][j]) {
                        flag = true;
                        break;
                    }
                }
                if(flag) {
                    break;
                }
            }
            if(!flag) {
                return true;
            }
        }
        return false;
    }

    private int[][] reverse(int[][] nums) {
        int m = nums.length;
        int n = nums[0].length;
        int[][] temp = new int[m][n];
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                temp[j][m - i - 1] = nums[i][j];
            }
        }
        return temp;
    }
}
