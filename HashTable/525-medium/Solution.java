import java.util.*;

public class Solution {
    public int findMaxLength(int[] nums) {
        //使用哈希表，从前往后遍历，存储的是当前位置的往前数的1和0的个数的差值，以及对应的位置
        //这里的位置，nums[i]则对应的位置尾 i + 1，哈希表只存储相同值下位置小的数
        Map<Integer, Integer> map = new HashMap<>();
        int zeroCount = 0;
        int oneCount = 0;
        map.put(0, 0);
        int ans = 0;
        for(int i = 0;i < nums.length;i++) {
            if(nums[i] == 1) {
                oneCount++;
            } else {
                zeroCount++;
            }
            int delt = oneCount - zeroCount;
            if(map.containsKey(delt)) {
                ans = Math.max(ans, i + 1 - map.get(delt));
            } else {
                map.put(delt, i + 1);
            }
        }
        return ans;
    }
}
