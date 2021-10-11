package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        // 排序，判断左端点是否在上一个区间内
        // 普遍解法，不优化空间
        List<int[]> arr = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0] != 0 ? a[0] - b[0] : a[1] - b[1]);
        int off = 0;
        while (off < intervals.length) {
            int[] temp = intervals[off];
            while (off < intervals.length && intervals[off][0] <= temp[1]) {
                temp[1] = Math.max(intervals[off++][1], temp[1]);
            }
            arr.add(temp);
        }
        int[][] ret = new int[arr.size()][0];
        for (int i = 0; i < arr.size(); i++) {
            ret[i] = arr.get(i);
        }
        return ret;
    }
}
