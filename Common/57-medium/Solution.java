package src;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][] { { newInterval[0], newInterval[1] } };
        }

        int left, right;
        right = -1;
        left = intervals.length;
        for (int i = 0; i < intervals.length; i++) {
            // 更新左边界
            if (left == intervals.length) {
                if (newInterval[0] <= intervals[i][1]) {
                    left = i;
                    newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                }
            }

            // 更新右边界
            if (newInterval[1] >= intervals[i][0]) {
                right = i;
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
        }

        int len = intervals.length - (right - left + 1) + 1;
        int[][] ret = new int[len][2];
        int off = 0;
        if (left > right) {
            for (int i = 0; i < intervals.length; i++) {
                if (i == left) {
                    ret[off++] = newInterval;
                }
                ret[off++] = intervals[i];
            }
            if (left == intervals.length) {
                ret[off++] = newInterval;
            }
        } else {
            for (int i = 0; i < left; i++) {
                ret[off++] = intervals[i];
            }
            ret[off++] = newInterval;
            for (int i = right + 1; i < intervals.length; i++) {
                ret[off++] = intervals[i];
            }
        }
        return ret;
    }
}
