import java.util.*;

public class Pro2 {
    public int reductionOperations(int[] nums) {
        //对数组排序，然后统计每个数字对应的出现频次，使用map
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int range = 0;
        int ans = 0;
        int preNum = nums[0];
        for(int i = 1;i < nums.length;i++) {
            if(nums[i] == preNum) {
                continue;
            } else {
                preNum = nums[i];
                ans += range * map.get(nums[i - 1]);
                range++;
            }
        }
        ans += range * map.get(nums[nums.length - 1]);
        return ans;
    }
}
