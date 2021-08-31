class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        // 首先尝试暴力求解
        // 直接对bookings的每一组所对应的下标来进行相应位置的加法
        int[] ans = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            int start = bookings[i][0] - 1;
            int end = bookings[i][1] - 1;
            for (int j = start; j <= end; j++) {
                ans[j] += bookings[i][2];
            }
        }
        return ans;
    }
}
