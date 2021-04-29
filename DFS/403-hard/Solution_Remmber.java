import java.util.*;

//普通递会超时，所以采用的是记忆化的递归
//使用对象数组来进行存储step-ret对
public class Solution_Remmber {
    //用来存储数组元素对，通过map来快速查找目标值是否在数组中
    Map<Integer, Integer> map;
    //对象数组
    //Object[] rem;
    //使用数组里面存储Map，但是这种方式似乎速度会慢接近2倍？？？
    HashMap<Integer, Boolean>[] rem;

    public boolean canCross(int[] stones) {
        if(stones[1] != 1) {
            return false;
        }
        map = new HashMap<>();
        //对象数组初始化
        //rem = new Object[stones.length];
        //另一种方法初始化
        rem = new HashMap[stones.length];
        Arrays.fill(rem, null);
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

        HashMap<Integer, Boolean> tempMap = new HashMap<>();
        if(rem[index] == null) {
            rem[index] = tempMap;
        } else {
            //做类型转换
            //tempMap = (HashMap)rem[index];
            //不需要做类型转换
            tempMap = rem[index];
            if(tempMap.containsKey(step)) {
                return tempMap.get(step);
            }
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

        tempMap.put(step, ret);
        return ret;
    }
}
