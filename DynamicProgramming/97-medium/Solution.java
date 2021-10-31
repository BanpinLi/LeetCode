package src;

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        // 动态规划，相当于找到一条路径，从[0,0]到[len1, len2]
        // 路径寻找要求每次只能向右或者向下，每次都必须匹配s3中的字符
        // dp[i][j] = (dp[i - 1][j] && s1[i] == s3[p]) || ...
        // _ 0 1 2 3 4
        // 0 T T
        // 1 _ T
        // 2 _ T T T
        // 3 _ _ _ T T
        // 以一种类似于走楼梯的方式来进行的状态转移，也就是向下选i，向右选j
        // 保证了i和j是交错的，并且不会出现选择i或者选择j的次数的绝对值之差，超过1

        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        // 预处理第一行和第一列
        for (int i = 1; i < s1.length() + 1; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int j = 1; j < s2.length() + 1; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                dp[i][j] |= dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            }
        }

        return dp[s1.length()][s2.length()];
    }
}