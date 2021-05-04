import java.util.Arrays;

public class Pro4 {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int[] ans = new int[queries.length];
        Arrays.fill(ans, Integer.MAX_VALUE);

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);        
        //预处理一下intervals的长度
        int[] len = new int[intervals.length];
        for(int i = 0;i < intervals.length;i++) {
            len[i] = intervals[i][1] - intervals[i][0] + 1;
        }

        for(int i = 0;i < queries.length;i++) {
            int left = 0, right = intervals.length - 1;
            int mid, sub = -1;
            while(left <= right) {
                mid = (left + right) / 2;

                if(intervals[mid][0] > queries[i]) {
                    right = mid - 1;
                } else {
                    if(mid == intervals.length - 1 || intervals[mid + 1][0] > queries[i]) {
                        sub = mid;
                        break;
                    } else {
                        left = mid + 1;
                    }
                }
            }

            if(sub == -1) continue;

            for(int j = 0;j <= sub;j++) {
                if(intervals[j][1] >= queries[i]) {
                    ans[i] = Math.min(ans[i], len[j]);
                }
            }
        }

        for(int i = 0;i < ans.length;i++) {
            if(ans[i] == Integer.MAX_VALUE) {
                ans[i] = -1;
            }
        }
        return ans;
    }
}
