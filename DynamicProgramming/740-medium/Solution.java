import java.util.*;

public class Solution {
    public int deleteAndEarn(int[] nums) {
        //将元素进行排序，并统计对应元素的频次
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //新建立一个数组将map里面的元素存储进去，并进行排序
        nums = new int[map.size()];
        int i = 0;
        for(int num : map.keySet()) {
            nums[i++] = num;
        }
        Arrays.sort(nums);

        //是用动态规划，确定状态转移方程为
        /*dp[i][0]表示第i个元素没有获得点数的最优解，同理dp[][1]则相反
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] =  前后相连: dp[i - 1][0] + nums[i] * n;
                        前后不相连： max(dp[i - 1][0], dp[i - 1][1]) + nums[i] * n;
        */
        int[][] dp = new int[nums.length][2];
        //初始化dp数组
        dp[0][1] = nums[0] * map.get(nums[0]);
        //开始状态转移
        for(i = 1;i < nums.length;i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            if(nums[i] == nums[i - 1] + 1) {
                dp[i][1] = dp[i - 1][0] + nums[i] * map.get(nums[i]);
            } else {
                dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]) + nums[i] * map.get(nums[i]);
            }
        }

        return Math.max(dp[i - 1][1], dp[i - 1][0]);
    }
}
