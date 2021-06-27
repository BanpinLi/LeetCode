public class Pro3 {
    //错了，在状态为dp[][0]的时候，并不能这样进行转移，结果会导致，出现两个连续的相同状态
    public long maxAlternatingSum(int[] nums) {
        //一个背包问题，使用动态规划，dp[][3]表示状态是不要、加上、减去
        //递推方程是:dp[i][0] = dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]
        //dp[i][1] = nums[i] + (dp[i - 1][0], dp[i - 1][2])
        //dp[i][2] = -nums[i] + (dp[i - 1][0], dp[i - 1][1])
        //最终返回i位置的绝对值大小
        int[][] dp = new int[nums.length][3];
        //初始化
        dp[0][1] = nums[0];
        dp[0][2] = -nums[0];
        for(int i = 1;i < nums.length;i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
            dp[i][1] = nums[i] + Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = -nums[i] + Math.max(dp[i - 1][0], dp[i - 1][1]);
        }

        int n = nums.length - 1;
        return Math.max(Math.max(Math.abs(dp[n][0]), Math.abs(dp[n][1])), Math.abs(dp[n][2]));
    }
}
