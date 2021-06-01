import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        //对数组进行排序，然后确定一个数字，另外剩下的数组来获得两个数字的最接近之和
        //对于寻找最接近的target可以使用双指针，当前比target大移动右指针，否则移动左指针
        //总的时间复杂度为 O(n²)
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for(int i = 0;i < nums.length - 2;i++) {
            int temp = twoSumClosest(nums, i + 1, target - nums[i]);
            if(Math.abs(temp + nums[i] - target) < Math.abs(ans - target)) {
                ans = temp + nums[i];
            }
            if(ans == target) {
                return ans;
            }
        }
        return ans;
    }

    private int twoSumClosest(int[] nums, int start, int target) {
        int left = start;
        int right = nums.length - 1;
        int ans = Integer.MAX_VALUE >> 2;
        while(left < right) {
            int temp = nums[left] + nums[right];
            if(temp > target) {
                right--;
            } else if(temp < target) {
                left++;
            } else {
                return target;
            }
            if(Math.abs(temp - target) < Math.abs(ans - target)) {
                ans = temp;
            }
        }
        return ans;
    }
}