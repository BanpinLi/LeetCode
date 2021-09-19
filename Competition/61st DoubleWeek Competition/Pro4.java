package src;

import java.util.*;

public class Pro4 {
    public int minOperations(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int maxNum = nums[len - 1];
        // 预处理相等元素
        int count = 0;
        for (int i = 1; i < len; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
                nums[i - 1] = maxNum + 1;
            }
        }
        Arrays.sort(nums);
        int maxLen = 1;
        int right = 0;
        int tempLen = 1;
        for (int i = 0; i < len; i++) {
            tempLen--;
            while (right < len - count && nums[right] <= nums[i] + len - 1) {
                tempLen++;
                right++;
            }
            maxLen = Math.max(maxLen, tempLen);
            if (right == len - count) {
                break;
            }
        }

        return len - maxLen;
    }
}
