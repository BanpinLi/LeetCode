import java.util.*;

//普通递归
public class Solution {
    //用来存储数组元素对，通过map来快速查找目标值是否在数组中
    Map<Integer, Integer> map;

    public boolean canCross(int[] stones) {
        if(stones[1] != 1) {
            return false;
        }
        map = new HashMap<>();
        for(int i = 0; i < stones.length; i++) {
            map.put(stones[i], i);
        }
        return dfs(stones, 1, stones[1]);
    }

    //代表是否能够到达河对岸
    private boolean dfs(int[] nums, int index, int step) {
        //index代表的是从index开始起跳，step是进行跳跃的限制
        if(index == nums.length - 1) {
            return true;
        }

        boolean ret = false;
        //进行跳跃，step、step+1、step-1
        if(map.containsKey(nums[index] + step)) {
            ret |= dfs(nums, map.get(nums[index] + step), step);
        }
        if(step > 1 && map.containsKey(nums[index] + step - 1)) {
            ret |= dfs(nums, map.get(nums[index] + step - 1), step - 1);
        }
        if(step != Integer.MAX_VALUE && map.containsKey(nums[index] + step + 1)) {
            ret |= dfs(nums, map.get(nums[index] + step + 1), step + 1);
        }

        return ret;
    }
}