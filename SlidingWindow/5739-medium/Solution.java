class Solution {
    public static void main(String[] args) {
        int[] nums = {1,2,4};
        System.out.println(maxFrequency(nums, 5));
    }

    public static int maxFrequency(int[] nums, int k) {
        int len = nums.length;
        int[] sum = new int[len + 1];
        for(int i = 0; i < len; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        int left, right;
        left = right = len;
        int ans = 1;
        while(left > 0) {
            int delt = (right - left + 1) * nums[right - 1] - (sum[right] - sum[left - 1]);
            if(delt <= k) {
                //满足条件
                ans = Math.max(ans, right - left + 1);
            } else {
                right--;
            }
            left--;
        }
        return ans;
    }
}