class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        // 让公主取救王子，右下角到左下角的动态规划
        // dp[i][j]代表的是，从当前位置到达公主位置所需要的最小生命值
        // dp[i][j] = max(min(dp[i + 1], dp[i][j + 1]) - dung[i][j], 1)
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        // 进行初始化
        dp[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);
        // 初始化列
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }
        // 初始化行
        for (int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] = Math.max(dp[m - 1][j + 1], 1);
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1);
            }
        }

        return dp[0][0];
    }
}