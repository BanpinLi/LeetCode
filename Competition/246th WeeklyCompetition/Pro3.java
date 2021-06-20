import java.util.*;

public class Pro3 {
    private List<List<int[]>> pathArray;
    private boolean[][] used;

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        //优先深度搜索，搜索过的位置用used来进行标记，每一次使用path来记录路径，最终更新到pathArray里面
        int row = grid2.length;
        int col = grid2[0].length;
        pathArray = new ArrayList<>();
        used = new boolean[row][col];
        for(int i = 0;i < row;i++) {
            for(int j = 0;j < col;j++) {
                if(grid2[i][j] == 1 && !used[i][j]) {
                    List<int[]> path = new ArrayList<>();
                    dfs(i, j, path, grid2);
                    pathArray.add(new ArrayList<int[]>(path));
                }
            }
        }
        int ans = 0;
        for(List<int[]> list : pathArray) {
            boolean flag = true;
            for(int[] subs : list) {
                if(grid1[subs[0]][subs[1]] == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                ans++;
            }
        }
        return ans;
    }

    private void dfs(int row, int col, List<int[]> path, int[][] nums) {
        if(row == nums.length || row < 0 || col < 0 || col == nums[0].length) {
            return;
        }
        
        if(nums[row][col] == 0 || used[row][col]) {
            return;
        }

        int[] temp = new int[]{row, col};
        path.add(temp);
        used[row][col] = true;
        dfs(row + 1, col, path, nums);
        dfs(row, col + 1, path, nums);
        dfs(row - 1, col, path, nums);
        dfs(row, col - 1, path, nums);
    }
}
