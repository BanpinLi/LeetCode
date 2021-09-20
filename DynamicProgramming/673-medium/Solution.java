package src;

public class Solution {
    public int findNumberOfLIS(int[] nums) {
        // dp[i][0]表示到i为止，最长的递增子序列的长度
        // dp[i][1]表示到i位置，最长的递增子序列的个数
        // dp[i][0] = dp[j][0] + 1(nums[i] > nums[j]) 否则为1
        // 当dp[j][0]是一样的时候，dp[i][1] += dp[j][1]
        int[][] dp = new int[nums.length][2];
        int maxLen = 1;
        dp[0][1] = dp[0][0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int tempMaxLen = 0;
            dp[i][0] = dp[i][1] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j][0] > tempMaxLen) {
                        tempMaxLen = dp[j][0];
                        dp[i][1] = dp[j][1];
                        dp[i][0] = dp[j][0] + 1;
                    } else if (dp[j][0] == tempMaxLen) {
                        dp[i][1] += dp[j][1];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i][0]);
        }

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i][0] == maxLen) {
                ans += dp[i][1];
            }
        }
        return ans;
    }
}
