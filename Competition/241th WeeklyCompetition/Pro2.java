public class Pro2 {
    public int minSpeedOnTime(int[] dist, double hour) {
        //使用二分查找，最快速度就是数组最大元素，最小速度就是最小元素
        int left = 1;
        int right = (int)1e7;
        int ans = Integer.MAX_VALUE;
        if(dist.length - 1 + (double)dist[dist.length - 1] / right > hour) {
            return -1;
        }
        while(left <= right) {
            int mid = (left + right) / 2;
            if(check(dist, hour, mid)) {
                right = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private boolean check(int[] dist, double hour, int speed) {
        double costTime = 0;
        for(int i = 0;i < dist.length - 1;i++) {
            if(dist[i] % speed == 0) {
                costTime += (int)(dist[i] / speed);
            } else {
                costTime += (int)(dist[i] / speed) + 1;
            }
            
            if(costTime > hour) {
                return false;
            }
        }
        costTime += dist[dist.length - 1] / (double)speed;
        if(costTime > hour) {
            return false;
        } else {
            return true;
        }
    }
}
