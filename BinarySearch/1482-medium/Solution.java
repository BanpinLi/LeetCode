public class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        //使用二分法来确定每一个可能符合条件的点，调用函数来判断是否能够组成花
        int min = 0, max = 0;
        for(int num : bloomDay) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int left = min, right = max;
        int ans = Integer.MAX_VALUE;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(canCreate(bloomDay, m, k, mid)) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private boolean canCreate(int[] nums, int m, int k, int limit) {
        //从前往后开始遍历，连续的就记录count++，一旦达到了k就m--，同时count置零
        //如果在中途中发现并不连续了，那么count直接置零，当m<=0的时候，就是能够制作
        int count = 0;  //用来记录连续的满足条件的个数
        for(int num : nums) {
            if(num <= limit) {
                count++;
                if(count == k) {
                    m--;
                    if(m == 0) {
                        return true;
                    }
                    count = 0;
                }
            } else {
                count = 0;
            }
        }

        return false;
    }
}
