class Solution {
    public boolean isMatch(String s, String p) {
        // 动态规划，dp[i][j]表示s[i]和p[j]的匹配情况，为true or false
        // 由dp[i][j]向上进行状态转移：
        // dp[i][j] -> dp[i + 1][j]:
        // ____ s[i + 1]一定为字母，p[i]有三种情况，字母、？、*
        // ________ 字母：dp[i + 1][j] = dp[i][j - 1] && s[i + 1] == p[j]
        // ________ ?：dp[i + 1][j] = dp[i][j - 1] ，因为s[i + 1]和p[j]一定可以发生匹配，且必须匹配
        // ________ *：dp[i + 1][j] = dp[i][j] ，因为p[j]是*，s多一个不多，少一个不少
        // dp[i][j] -> dp[i][j + 1]:
        // ____ s[i]为字母，p[j + 1]同样有三种情况，字母、？、*
        // ________ 字母：dp[i][j + 1] = dp[i - 1][j] && s[i] == p[j + 1]
        // ________ ?：dp[i][j + 1] = dp[i - 1][i]
        // ________ *：dp[i][j + 1] = dp[?][j]，也就是从0 - i，只要有一个是true，那么就为true

        // 简化成dp[i][j] <- dp[?][?]的形式：
        // ____ j为字母：dp[i][j] = dp[i - 1][j - 1] && s[i] == s[j]
        // ____ j为？：dp[i][j] = dp[i - 1][j - 1]
        // ____ j为*：dp[i][j] = dp[?][j]
        // ________ 另外还有一种方法，更容易理解和实现 dp[i][j] = dp[i - 1][j] || dp[i][j - 1]
        // 为了处理的方便起见，将dp[0][0]表示成两个空字符串的匹配，上面的表达式进行微调
        int lenp = p.length();
        int lens = s.length();
        boolean[][] dp = new boolean[lens + 1][lenp + 1];
        // 进行初始化操作
        // 初始化dp[?][0]这一行
        dp[0][0] = true;

        // 对于dp[i][j] = dp[0 - i][j - 1]的优化，根据填表顺序可以看到，每填完一行就记录下来这一样的||值
        boolean flag = false;

        for (int j = 1; j < lenp + 1; j++) {
            // 每次进入循环都要把flag置为false
            flag = false;
            if (p.charAt(j - 1) == '*') {
                for (int i = 0; i <= lens; i++) {
                    flag = flag || dp[i][j - 1];
                    dp[i][j] = flag;
                }
            } else if (p.charAt(j - 1) == '?') {
                // dp[0][j] = false;
                for (int i = 1; i < lens + 1; i++) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            } else {
                // dp[0][j] = false;
                for (int i = 1; i < lens + 1; i++) {
                    dp[i][j] = dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1);
                }
            }

        }

        return dp[lens][lenp];
    }
}