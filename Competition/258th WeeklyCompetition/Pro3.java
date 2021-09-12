public class Pro3 {
    private int ans;

    public int maxProduct(String s) {
        StringBuilder path1 = new StringBuilder();
        StringBuilder path2 = new StringBuilder();
        boolean[] used = new boolean[s.length()];
        dfs(path1, path2, s, 0, used, 0);
        return ans;
    }

    // 回溯
    private void dfs(StringBuilder path1, StringBuilder path2, String s, int depth, boolean[] used, int type) {
        if (depth == s.length()) {
            return;
        }
        if (type == 0) {
            path1.append(s.charAt(depth));
            used[depth] = true;
            if (isPalindrome(path1)) {
                dfs(path1, path2, s, 0, used, 1);
            }
            dfs(path1, path2, s, depth + 1, used, 0);
            used[depth] = false;
            path1.deleteCharAt(path1.length() - 1);

            dfs(path1, path2, s, depth + 1, used, 0);
        } else {
            if (used[depth] == false) {
                path2.append(s.charAt(depth));

                if (isPalindrome(path2)) {
                    ans = Math.max(ans, path1.length() * path2.length());
                }

                dfs(path1, path2, s, depth + 1, used, 1);
                path2.deleteCharAt(path2.length() - 1);
            }

            dfs(path1, path2, s, depth + 1, used, 1);
        }
    }

    private boolean isPalindrome(StringBuilder s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
