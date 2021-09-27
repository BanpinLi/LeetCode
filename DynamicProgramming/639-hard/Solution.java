package src;

class Solution {
    public int numDecodings(String s) {
        // 需要注意的是，输入的字符串并不是一定是满足条件的编码，需要做额外判断

        // 动态规划，dp[i]表示i位置的编码数量
        // 位置i分为三种情况，0，1-9，*
        // 0：dp[i] = dp[i - 2];
        // 1-9：dp[i] = dp[i - 1] + ?dp[i - 2]
        // * ：dp[i] = 9 * dp[i - 1] + ?dp[i - 2]
        // ？处的内容是要判断连续两位是否能够符合条件
        if (s.charAt(0) == '0') {
            return 0;
        }

        int mod = (int) 1e9 + 7;
        long prev = s.charAt(0) == '*' ? 9 : 1;
        long ans = prev;
        long ppre = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '0' || s.charAt(i - 1) > '2') {
                    // xx00 xx3-90
                    return 0;
                }
                ans = s.charAt(i - 1) == '*' ? 2 * ppre : ppre;
            } else if (s.charAt(i) == '*') {
                // xxx*
                ans = 9 * prev;
                if (s.charAt(i - 1) == '1') {
                    // xx1*
                    ans += ppre * 9;
                } else if (s.charAt(i - 1) == '2') {
                    // xx2*
                    ans += ppre * 6;
                } else if (s.charAt(i - 1) == '*') {
                    // xx**
                    ans += ppre * 15;
                }
            } else {
                // xxx1-9
                ans = prev;
                if (s.charAt(i - 1) == '*') {
                    // xx*1-9
                    ans += s.charAt(i) > '6' ? ppre : 2 * ppre;
                } else if (s.charAt(i - 1) != '0') {
                    // xx1-9 1-9
                    int num = Integer.parseInt(s.substring(i - 1, i + 1));
                    ans += num <= 26 ? ppre : 0;
                }
            }
            ppre = prev;
            ans %= mod;
            prev = ans;

        }
        return (int) (ans % mod);
    }
}
