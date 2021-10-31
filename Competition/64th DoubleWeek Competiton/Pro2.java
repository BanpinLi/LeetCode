package src;

import java.util.Arrays;

public class Pro2 {
    public int maxTwoEvents(int[][] events) {
        // 排序
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int curMaxValue = events[events.length - 1][2];
        int[] value = new int[events.length];
        value[value.length - 1] = curMaxValue;
        for (int i = value.length - 2; i >= 0; i--) {
            curMaxValue = Math.max(curMaxValue, events[i][2]);
            value[i] = curMaxValue;
        }

        int ans = 0;
        for (int i = 0; i < events.length; i++) {
            int temp = events[i][2];
            int retIndex = findPosition(events[i][1] + 1, events);
            if (retIndex != -1) {
                temp += value[retIndex];
            }
            ans = Math.max(ans, temp);
        }
        return ans;
    }

    // 找到第一个target所在的位置
    public int findPosition(int target, int[][] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid][0]) {
                return mid;
            } else if (nums[mid][0] < target) {
                left = mid + 1;
            } else {
                if (mid == 0) {
                    return 0;
                }
                if (nums[mid - 1][0] < target) {
                    return mid;
                }
                right = mid - 1;
            }
        }
        return -1;
    }
}
