import java.util.*;

class Solution {
    //超时了
    public int sumOfFlooredPairs(int[] nums) {
        //排序
        long ans = nums.length;
        Arrays.sort(nums);
        int flag = nums[0];
        int count = 0;
        //统计相同元素个数
        for(int i = 0;i < nums.length;i++) {
            if(nums[i] == flag) {
                count++;
            } else {
                ans += (count - 1) * count / 2;
                if(ans > Integer.MAX_VALUE) {
                    ans %= (long)(1e9 + 7);
                }
                count = 1;
                //i--;
                flag = nums[i];
            }
        }
        ans += (count - 1) * count / 2;
        for(int i = nums.length - 1;i > 0;i--) {
            for(int j = 0;j < i;j++) {
                ans += nums[i] / nums[j];
                if(ans > Integer.MAX_VALUE) {
                    ans %= (long)(1e9 + 7);
                }
            }
        }
        return (int)(ans % (int)(1e9 + 7));
    }
}