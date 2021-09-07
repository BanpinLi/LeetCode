import java.util.*;

class Solution {
    // 全局变量，用来存储答案
    private List<List<String>> ans;

    public List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        boolean[][] used = new boolean[n][n];
        dfs(n, 0, path, used);
        return ans;
    }

    // 对每一层进行递归，然后填入相应的位置，并进行回溯
    private void dfs(int n, int depth, ArrayList<String> path, boolean[][] used) {
        if (depth == n) {
            ans.add(new ArrayList<>(path));
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            sb.append('.');
        }
        for (int j = 0; j < n; j++) {
            if (used[depth][j]) {
                continue;
            }
            // 开始回溯
            sb.insert(j, 'Q');
            path.add(sb.toString());
            ArrayList<int[]> subList = usedNumsToTrue(used, depth, j);
            for (int[] sub : subList) {
                int row = sub[0];
                int col = sub[1];
                used[row][col] = true;
            }

            dfs(n, depth + 1, path, used);

            path.remove(path.size() - 1);
            sb.deleteCharAt(j);
            for (int[] sub : subList) {
                int row = sub[0];
                int col = sub[1];
                used[row][col] = false;
            }
        }
    }

    // 给一个数组，传递一个下标，然后返回一个列表
    // 返回的列表里面表示横行、纵行、斜行需要置为true，但是没有的下标对
    private ArrayList<int[]> usedNumsToTrue(boolean[][] used, int row, int col) {
        ArrayList<int[]> ret = new ArrayList<>();
        int n = used.length;
        // 横行
        for (int j = 0; j < n; j++) {
            if (!used[row][j]) {
                ret.add(new int[] { row, j });
            }
        }

        // 纵行
        for (int i = 0; i < n; i++) {
            if (!used[i][col]) {
                ret.add(new int[] { i, col });
            }
        }

        // 斜行
        // 左上
        int i = row - 1;
        int j = col - 1;
        while (i >= 0 && j >= 0) {
            if (!used[i][j]) {
                ret.add(new int[] { i, j });
            }
            i--;
            j--;
        }
        // 左下
        i = row + 1;
        j = col - 1;
        while (i <= n - 1 && j >= 0) {
            if (!used[i][j]) {
                ret.add(new int[] { i, j });
            }
            i++;
            j--;
        }
        // 右上
        i = row - 1;
        j = col + 1;
        while (i >= 0 && j <= n - 1) {
            if (!used[i][j]) {
                ret.add(new int[] { i, j });
            }
            i--;
            j++;
        }
        // 右下
        i = row + 1;
        j = col + 1;
        while (i <= n - 1 && j <= n - 1) {
            if (!used[i][j]) {
                ret.add(new int[] { i, j });
            }
            i++;
            j++;
        }

        return ret;
    }
}