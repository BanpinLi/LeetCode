class Solution {
    //一道相对简单的动态规划，主要比较坑的点是dp数组会溢出，以及空间分配多而超出内存限制
    public int numWays(int steps, int arrLen) {
        //使用动态规划 dp[i][j]表示在位置j使用i步能不能到达，动态规划的递推关系为：
        //dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] + dp[i - 1][j + 1] 前提是数组不会越界
        //根据数据的特性，将dp的长度缩短，具体来说就是过长的arrLen并没有什么意义
        if(arrLen > steps) {
            arrLen = steps + 1;
        }
        long[][] dp = new long[steps + 1][arrLen];
        //初始化dp数组：
        for(int i = 1;i < Math.min(steps + 1, arrLen);i++) {
            dp[i][i] = 1;
        }
        //一个特殊的边界条件
        dp[1][0] = 1;
        for(int i = 2;i < steps + 1;i++) {
            for(int j = 0;j < arrLen;j++) {
                //这表示一定不可能到达目标地点
                if(i < j) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j];
                if(j != 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if(j != arrLen - 1) {
                    dp[i][j] += dp[i - 1][j + 1];
                }
                if(dp[i][j] > Integer.MAX_VALUE) {
                    dp[i][j] %= (long)(1e9 + 7);
                }
            }
        }
        return (int)(dp[steps][0] % (long)(1e9 + 7));
    }
}