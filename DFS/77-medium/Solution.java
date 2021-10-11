package src;

import java.util.*;

class Solution {
    private List<List<Integer>> ans;

    public List<List<Integer>> combine(int n, int k) {
        ans = new LinkedList<>();
        dfs(new LinkedList<>(), 1, k, n);
        return ans;
    }

    private void dfs(LinkedList<Integer> path, int depth, int k, int n) {
        if (path.size() == k) {
            ans.add(new LinkedList<>(path));
            return;
        }

        if (depth > n) {
            return;
        }
        if (n - depth + 1 + path.size() < k) {
            return;
        }

        path.add(depth);
        dfs(path, depth + 1, k, n);
        path.removeLast();

        dfs(path, depth + 1, k, n);
    }
}
