import java.util.*;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        //获得数组的前缀和，仿造两数之和的思路
        //从前往后开始遍历，在map里面寻找targe - sum[i]，找到几个那么结果ans几个
        
        //获得前缀和，前缀和是包含了当前元素的
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i = 1;i < nums.length;i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        //创建map并使用(0,1)进行初始化，代表前缀和为0的已经有了，就是prifixSum[-1]
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int ans = 0;
        for(int i = 0;i < nums.length;i++) {
            //注意这里是prefixSum - k而不是反过来的
            if(map.containsKey(prefixSum[i] - k)) {
                ans += map.get(prefixSum[i] - k);
            }

            //对于prifixSum则是有则更新，无则添加，善用getOrDefault方法减少代码的长度
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);   
            /*if(map.containsKey(prefixSum[i])) {
                map.put(prefixSum[i], map.get(prefixSum[i]) + 1);
            } else {
                map.put(prefixSum[i], 1);
            }*/
        }

        return ans;
    }
}
