import java.util.*;

class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            int x0 = points[i][0];
            int y0 = points[i][1];
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                int x = points[j][0];
                int y = points[j][1];
                int dis = (x - x0) * (x - x0) + (y - y0) * (y - y0);
                map.put(dis, map.getOrDefault(dis, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int value = entry.getValue();
                ans += value * (value - 1);
            }
        }
        return ans;
    }
}