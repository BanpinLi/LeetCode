import java.util.*;

class Solution {
    // public String findLongestWord(String s, List<String> dictionary) {
    // String ans = "";
    // for (String p : dictionary) {
    // if (isSub(s, p)) {
    // if (p.length() > ans.length()) {
    // ans = p;
    // } else if (p.length() == ans.length()) {
    // if (ans.compareTo(p) > 0) {
    // ans = p;
    // }
    // }
    // }
    // }
    // return ans;
    // }

    // // 判断两个字符串是否满足p是s的一个子序列
    // private boolean isSub(String s, String p) {
    // if (s.length() < p.length()) {
    // return false;
    // }

    // // 双指针，一个字符串一个
    // int sp = 0;
    // int pp = 0;
    // while (sp < s.length()) {
    // while (sp < s.length() && s.charAt(sp) != p.charAt(pp)) {
    // sp++;
    // }
    // if (sp < s.length()) {
    // sp++;
    // pp++;
    // if (pp == p.length()) {
    // return true;
    // }
    // }
    // }
    // return false;
    // }

    public String findLongestWord(String s, List<String> dictionary) {
        // 在前面的判断p是否是s的一个子序列的时候是在s的位置进行不断移动
        // 如果采用一个dp[i][ch]表示从i位置开始，下一个ch是什么位置，如果没有，就置为-1
        // dp[i][ch] = dp[i + 1][ch]; dp[i][s[i]] = i + 1;

        int slen = s.length();
        int[][] dp = new int[slen][26];
        // 初始化
        for (int i = 0; i < 26; i++) {
            dp[slen - 1][i] = -1;
        }
        // 开始推导
        for (int i = slen - 2; i >= 0; i--) {
            for (int ch = 0; ch < 26; ch++) {
                dp[i][ch] = dp[i + 1][ch];
            }
            dp[i][s.charAt(i + 1) - 'a'] = i + 1;
        }

        String ans = "";
        for (String p : dictionary) {
            if (p.length() < ans.length()) {
                continue;
            }

            // 判断p是否是s的一个子序列
            int pp = 0; // 指向p字符串的指针
            if (p.charAt(pp) == s.charAt(0)) {
                // 引入了dp数组之后，没办法对s[0]进行判断，所以需要额外确定pp的开始判断位置
                pp = 1;
            }
            int sIndex = 0;
            while (pp < p.length()) {
                char ch = p.charAt(pp);
                sIndex = dp[sIndex][ch - 'a'];
                if (sIndex == -1) {
                    break;
                }
                pp++;
            }
            if (pp == p.length()) {
                // 表示是一个子序列，将其变为ans
                if (p.length() > ans.length() || p.compareTo(ans) < 0) {
                    ans = p;
                }
            }
        }
        return ans;
    }
}