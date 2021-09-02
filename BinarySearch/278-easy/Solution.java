public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        //一个比较简单的二分查找，需要考虑到的事情是int数值的越界问题
        int ans = n;
        int left = 1;
        int right = n;
        while(left <= right) {
            int mid = (int)(((long)left + right) / 2);
            if(isBadVersion(mid)) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}