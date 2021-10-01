package src;

class Solution {
    public int maxArea(int[] height) {
        // 左右指针，每一次都移动相对小的那个指针，实时记录ans
        // 算法原理：小指针是木桶的短边，当小指针移动的时候，水量可能会增大
        // 当移动大指针的时候，水量只可能不变或者减少，是贪心的算法
        int left = 0;
        int right = height.length - 1;
        int ans = 0;
        while (left <= right) {
            ans = Math.max(ans, Math.min(height[left], height[right]) * (right - left));
            if (height[right] < height[left]) {
                right--;
            } else {
                left++;
            }
        }
        return ans;
    }
}