package src;

import java.util.Arrays;

public class Pro2 {
    public int minOperations(int[][] grid, int x) {
        int[] nums = twoToOne(grid);
        Arrays.sort(nums);
        int[] diff = new int[nums.length];
        for (int i = 1; i < diff.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
        for (int i = 1; i < diff.length; i++) {
            if (diff[i] % x != 0) {
                return -1;
            }
        }

        // 以nums[0]为起点，每次向上移动x，实时更新ans
        int ans = 0;
        for (int num : nums) {
            ans += ((num - nums[0]) / x);
        }

        int off = 1;
        while (off < nums.length && nums[0] == nums[off]) {
            off++;
        }
        int pNum = nums[0] + x;
        int left = 0;
        int right = ans;
        for (; pNum <= nums[nums.length - 1]; pNum += x) {
            left += off;
            right -= (nums.length - off);
            while (off < nums.length && pNum == nums[off]) {
                off++;
            }
            ans = Math.min(ans, left + right);
        }
        return ans;
    }

    private int[] twoToOne(int[][] nums) {
        int m = nums.length;
        int n = nums[0].length;
        int[] ret = new int[m * n];
        int off = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret[off++] = nums[i][j];
            }
        }
        return ret;
    }
}
