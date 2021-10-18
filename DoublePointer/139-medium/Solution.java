package src;

class Solution {
    public void rotate(int[] nums, int k) {
        // 两种方法，比较经典的是，反转三次
        // 123456 -> 654321 -> 564321 -> 561234
        k %= nums.length;
        rotate(nums, 0, nums.length);
        rotate(nums, 0, k);
        rotate(nums, k, nums.length);
    }

    // 旋转start到end，不包括end
    private void rotate(int[] nums, int start, int end) {
        int left = start;
        int rigth = end - 1;
        while (left <= rigth) {
            int temp = nums[left];
            nums[left++] = nums[rigth];
            nums[rigth--] = temp;
        }
    }
}