class Solution {
    public int countDigitOne(int n) {
        // if (n == 0) {
        // return 0;
        // }

        // // 获得n的每一位，但是是反序的，1234 -> 4 3 2 1
        // int[] bits = new int[10];
        // int ans = 0;
        // int len = 0;
        // int temp = n;
        // while (temp > 0) {
        // int bit = temp % 10;
        // if (bit == 1) {
        // // 判断n的1的个数，初始化ans
        // ans++;
        // }
        // bits[len++] = bit;
        // temp /= 10;
        // }

        // // 获得0、9、99、999...所对应的1的个数
        // int[] dp = new int[len];
        // int times = 1;
        // for (int i = 1; i < len; i++) {
        // dp[i] = dp[i - 1] * 10 + times;
        // times *= 10;
        // }

        // // 1234 -> 0000-0999 + 234 + 0000-0234 -> 300 + 234 + ...
        // // 234 -> 000-199 + 0 + 200-234 -> ...
        // // 34 -> 00-29 + 0 + 30-34 -> ...
        // // 4 -> 0-3 + 0 + 4 -> ...
        // times = (int) Math.pow(10, len - 1);
        // for (int i = len - 1; i >= 0; i--) {
        // if (bits[i] == 1) {
        // ans += dp[i] + n % times;
        // } else if (bits[i] > 1) {
        // ans += dp[i] * bits[i] + times;
        // }
        // times /= 10;
        // }
        // return ans;

        // 不要脸优化版本
        int temp = n, times = 1, dp = 0, ans = 0;
        while (temp > 0) {
            int bit = temp % 10;
            ans += bit == 0 ? 0 : (dp * bit + (bit == 1 ? (n % times + 1) : times));
            dp = 10 * dp + times;
            times *= 10;
            temp /= 10;
        }
        return ans;
    }
}