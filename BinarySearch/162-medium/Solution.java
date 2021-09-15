class Solution {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[len - 1] > nums[len - 2]) {
            return len - 1;
        }

        // 二分查找，查找的mid点如果实在上升区间，那么峰值在右端，在下降区间，峰值在左端
        int left = 1;
        int right = len - 2;
        int ret = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                if (nums[mid - 1] < nums[mid]) {
                    ret = mid;
                    break;
                } else {
                    right = mid - 1;
                }
            }
        }
        return ret;
    }
}