import java.util.*;

class Solution {

    private List<List<Integer>> ans;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 一道比较经典的回溯题
        // 先将数组进行排序，虽然不排序也可以，如果不进行排序的话，去重就不是很方便了
        Arrays.sort(candidates);
        ans = new ArrayList<>();
        // 使用一个head来表示一次回溯中同等级的选择，保证在同一个位置不选择两次，去重

        ArrayList<Integer> path = new ArrayList<>();
        dfs(path, candidates, 0, 0, target);

        return ans;
    }

    private void dfs(ArrayList<Integer> path, int[] candidates, int depth, int sum, int target) {
        // 回溯的终点有三个：深度达到最大，总和相等，总和超过目标值
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        } else if (sum > target) {
            return;
        }
        // 应当在三个条件的最后一个条件判断深度，因为先判断深度会造成误判
        if (depth == candidates.length) {
            return;
        }

        int flag = -1;
        for (int i = depth; i < candidates.length; i++) {
            if (candidates[i] == flag) {
                continue;
            } else {
                flag = candidates[i];
            }

            // 从这里开始回溯操作，将一个数字放进去
            path.add(candidates[i]);

            // 每次写回溯必定在决定下一个回溯起点的位置出问题！！！
            dfs(path, candidates, i + 1, sum + candidates[i], target);
            // 回溯结束，讲放进去的数字吐出来
            path.remove(path.size() - 1);
        }
    }
}
