package src;

public class SolutionDoubleP {
    public int trap(int[] height) {
        // 使用双指针
        // left和right指针进行两边向中间移动，同时维护一个leftmax和rightmax，来表示柱子的最大高度
        // 由于最大边界的特性，导致left和right中的较小者必定位于界限之中，也就是有积水
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        int ans = 0;

        while (left <= right) {
            // 首先更新边界
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (height[left] < height[right]) {
                ans += Math.min(leftMax, rightMax) - height[left++];
            } else {
                ans += Math.min(leftMax, rightMax) - height[right--];
            }
        }
        return ans;
    }
}
