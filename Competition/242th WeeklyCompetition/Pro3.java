public class Pro3 {
    ///超时了
    public boolean canReach(String s, int minJump, int maxJump) {
        //动态规划：dp[i]表示是否能够到达
        //dp[i] = dp[i-min] and dp[i-max]
        //为了避免边界问题，我们对前面的max个元素提前进行处理
        boolean[] dp = new boolean[s.length()];
        dp[0] = true;
        for(int i = minJump;i <= maxJump;i++) {
            if(s.charAt(i) == '1') {
                continue;
            }
            for(int j = 0;j <= i - minJump;j++) {
                dp[i] = dp[i] || dp[j];
                if(dp[i]) {
                    continue;
                }
            }
        }
        for(int i = maxJump;i < s.length();i++) {
            if(s.charAt(i) == '1') {
                continue;
            }
            for(int j = i - maxJump;j <= i - minJump;j++) {
                dp[i] = dp[i] || dp[j];
                if(dp[i]) {
                    continue;
                }
            }
        }
        return dp[s.length() - 1];
    }
}
