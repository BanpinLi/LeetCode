class Solution {
    private int ans;

    public int totalNQueens(int n) {
        // 使用三个数组来代表放入某一个皇后之后，占用的位置
        // 分别是列colRec，左斜slashLeft，右斜slashRight相应位置为true时就代表不能放
        // 斜线利用的性质是：同一条左斜线上满足，row - col 相等；同一条右斜线上满足，row + col 相等。
        // 所以通过下标访问对应位置并置为true代表这个下标对应的斜线是不能放皇后的
        boolean[] colRec = new boolean[n];
        boolean[] slashLeft = new boolean[2 * n - 1];
        boolean[] slashRight = new boolean[2 * n - 1];

        dfs(n, 0, colRec, slashLeft, slashRight);

        return ans;
    }

    private void dfs(int n, int depth, boolean[] colRec, boolean[] slashLeft, boolean[] slashRight) {
        if (n == depth) {
            ans++;
            return;
        }
        for (int j = 0; j < n; j++) {
            // 检查这个位置对应的三条线
            if (colRec[j] || slashLeft[depth - j + n - 1] || slashRight[depth + j]) {
                continue;
            }

            // 选择j这个位置
            colRec[j] = slashLeft[depth - j + n - 1] = slashRight[depth + j] = true;

            dfs(n, depth + 1, colRec, slashLeft, slashRight);

            // 取消j这个位置
            colRec[j] = slashLeft[depth - j + n - 1] = slashRight[depth + j] = false;
        }
    }
}