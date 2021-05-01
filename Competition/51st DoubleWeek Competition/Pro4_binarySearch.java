import java.util.*;

public class Pro4_binarySearch {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        //优化暴力查询的方法，先对rooms根据第二个元素进行排序
        //然后通过二分查找来找到第一个满足面积条件的数字
        Arrays.sort(rooms, (a, b) -> a[1] - b[1]);
        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);
        for(int i = 0;i < queries.length;i++) {
            //开始进行二分查找
            int left = 0, right = rooms.length - 1;
            int mid;
            int target = queries[i][1];
            int sub = -1;    //用来记录符合条件的开始rooms开始下标
            
            //如果该循环非break退出，证明所有元素都不满足条件
            //可以进行预处理判断，当最大值小于target或者最小值大于target的时候可以直接结束
            while(left <= right) {
                mid = (left + right) / 2;
                //查找的终止条件为mid为0或者mid左不满足，右满足
                if(rooms[mid][1] < target) {
                    left = mid + 1;
                } else {
                    //mid为零其实也就是最小值也满足大于等于target
                    if(mid == 0 || rooms[mid - 1][1] < target) {
                        //代表符合条件
                        sub = mid;
                        break;
                    } else {
                        right = mid - 1;
                    }
                }
            }

            if(sub == -1) continue;

            int delt = Integer.MAX_VALUE;   //用来记录id的差值，用以判断应该怎样来更新ans
            for(int j = sub;j < rooms.length;j++) {
                //这代表有一个id差值更小的，就更新ans和delt
                if(delt > Math.abs(queries[i][0] - rooms[j][0])) {
                    delt = Math.abs(queries[i][0] - rooms[j][0]);
                    ans[i] = rooms[j][0];
                } else if(delt == Math.abs(queries[i][0] - rooms[j][0])) {
                    ans[i] = Math.min(ans[i], rooms[j][0]);
                }
            }
        }

        return ans;
    }
}
