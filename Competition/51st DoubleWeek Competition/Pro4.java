import java.util.*;

public class Pro4 {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        //直接暴力查询
        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);
        for(int i = 0;i < queries.length;i++) {
            int delt = Integer.MAX_VALUE;   //用来记录id的差值
            for(int j = 0;j < rooms.length;j++) {
                if(queries[i][1] <= rooms[j][1]) {
                    if(delt > Math.abs(queries[i][0] - rooms[j][0])) {
                        delt = Math.abs(queries[i][0] - rooms[j][0]);
                        ans[i] = rooms[j][0];
                    } else if(delt == Math.abs(queries[i][0] - rooms[j][0])) {
                        ans[i] = Math.min(ans[i], rooms[j][0]);
                    }
                }
            }
        }
        return ans;
    }
}
