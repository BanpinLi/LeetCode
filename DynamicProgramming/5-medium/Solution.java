package src;

class Solution {
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        // 经典的动态规划dp[i][j]表示是否是回文子串
        // maxLen记录最长的回文子串的长度，当长度发生变化的时候，改变start和end
        int start, maxLen;
        start = maxLen = 1;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j > i + 1 ? dp[i + 1][j - 1] : true);
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
}
