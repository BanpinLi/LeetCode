package src;

import java.util.List;
import java.util.ArrayList;

class Solution {
    private List<List<String>> ans;

    public List<List<String>> partition(String s) {
        // 获得dp[i][i]是否是回文串
        // dp[i][j] = dp[i + 1][j - 1] && s[i] == s[j]
        ans = new ArrayList<>();

        boolean[][] dp = new boolean[s.length()][s.length()];
        // 初始化
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                dp[i][j] = (i + 1 == j ? true : dp[i + 1][j - 1]) && s.charAt(i) == s.charAt(j);
            }
        }
        List<String> path = new ArrayList<>();
        dfs(path, dp, s, 0);
        return ans;
    }

    // dpeth代表的是一个字符串开始的位置
    private void dfs(List<String> path, boolean[][] dp, String s, int depth) {
        if (depth == s.length()) {
            if (path.size() != 0) {
                ans.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = depth; i < s.length(); i++) {
            if (dp[depth][i]) {
                path.add(s.substring(depth, i + 1));
                dfs(path, dp, s, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}
