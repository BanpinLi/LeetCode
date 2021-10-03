package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private List<List<Integer>> ans;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 非常经典的回溯算法，主要在于去重的思路
        // 在一个位置面临选择的时候，这个位置必须满足
        // depth == 0 || nums[depth] != nums[depth - 1] || used[depth - 1]
        // 也就是当连续元素的选择间断的时候，就不应该再次进行选择了
        Arrays.sort(nums);
        ans = new ArrayList<>();
        dfs(new ArrayList<>(), 0, nums, new boolean[nums.length]);
        return ans;
    }

    private void dfs(List<Integer> path, int depth, int[] nums, boolean[] used) {
        if (depth == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        dfs(path, depth + 1, nums, used);

        if (depth == 0 || nums[depth] != nums[depth - 1] || used[depth - 1]) {
            path.add(nums[depth]);
            used[depth] = true;
            dfs(path, depth + 1, nums, used);
            used[depth] = false;
            path.remove(path.size() - 1);
        }
    }
}
