package src;

import java.util.*;

public class Pro3 {
    private int maxValue;
    private int ans;
    private List<Integer> path;

    public int countMaxOrSubsets(int[] nums) {
        // dfs
        maxValue = -1;
        ans = 0;
        path = new ArrayList<>();
        dfs(0, nums);
        return ans;
    }

    // 每个位置面临放进和不放两种选择
    private void dfs(int depth, int[] nums) {
        if(depth == nums.length) {
            int temp = orBit(path);
            if(temp > maxValue) {
                ans = 1;
                maxValue = temp;
            } else if(temp == maxValue) {
                ans++;
            }
            return;
        }

        dfs(depth + 1, nums);

        path.add(nums[depth]);
        dfs(depth + 1, nums);
        path.remove(path.size() - 1);
    }

    private int orBit(List<Integer> arr) {
        int ret = 0;
        for(int num : arr) {
            ret |= num;
        }
        return ret;
    }
}
