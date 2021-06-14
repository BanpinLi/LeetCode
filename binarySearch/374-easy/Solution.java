public class Solution extends GuessGame {
    public int guessNumber(int n) {
        //非常简单的二分查找，注意的是不要让int溢出就行
        int left = 1;
        int right = n;
        int ans = 0;
        while(left <= right) {
            int mid = (int)(((long)left + right) / 2);
            if(guess(mid) == 1) {
                left = mid + 1;
            } else if(guess(mid) == -1) {
                right = mid - 1;
            } else {
                ans = mid;
                break;
            }
        }
        return ans;
    }
}
