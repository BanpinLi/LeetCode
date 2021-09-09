import java.util.*;

class Solution {
    private Map<String, Boolean> map;
    private List<String> ans;

    public List<String> wordBreak(String s, List<String> wordDict) {
        // 存储wordDict里的String，便于后面快速进行查找
        map = new HashMap<>();
        ans = new ArrayList<>();

        for (String word : wordDict) {
            map.put(word, true);
        }

        List<String> path = new ArrayList<>();
        dfs(s, 0, path);

        return ans;
    }

    // start代表的是应该从s的start位置开始进行查找和回溯
    private void dfs(String s, int start, List<String> path) {
        // 分析逻辑可以得知，当start能够到达末尾时，说明path中的所有元素均满足条件，并且s被遍历
        if (start == s.length()) {
            ans.add(String.join(" ", path));
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (map.containsKey(sb.toString())) {
                path.add(sb.toString());
                dfs(s, i + 1, path);
                path.remove(path.size() - 1);
            }
        }
    }
}