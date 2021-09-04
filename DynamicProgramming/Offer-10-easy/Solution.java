class Solution {
    public int fib(int n) {
        // //动态规划罢了，但是注意溢出
        // if(n <= 1) {
        // return n;
        // }
        // long[] dp = new long[n + 1];
        // dp[1] = 1;
        // for(int i = 2;i <= n;i++) {
        // dp[i] = (dp[i - 1] + dp[i - 2]) % (int)(1e9 + 7);
        // }
        // return (int)dp[n];

        // 动态规划优化内存
        if (n <= 1) {
            return n;
        }

        long prepre = 0;
        long pre = 1;
        long ans = -1;
        for (int i = 2; i <= n; i++) {
            ans = (prepre + pre) % (int) (1e9 + 7);
            prepre = pre;
            pre = ans;
        }
        return (int) ans;
    }
}