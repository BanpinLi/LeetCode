package src;

class Solution {
    public int numDistinct(String s, String t) {
        if (s.length() < t.length())
            return 0;

        // 动态规划：dp[i][j]表示si到tj的最少方法
        // dp[i][j] = dp[i - 1][j] ->> s[i] != s[j]
        // dp[i][j] = dp[i - 1][j - 1] ->> s[i] == s[j]
        int[][] dp = new int[s.length()][t.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i && j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = j > 0 ? dp[i - 1][j - 1] : 1;
                }
                dp[i][j] += i > 0 ? dp[i - 1][j] : 0;
            }
        }
        return dp[s.length() - 1][t.length() - 1];
    }
}