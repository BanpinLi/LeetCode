public class Solution {
    //和比较经典的 0-1背包问题很相似，在某个位置的选择是是否将这个字符串放入
    public int findMaxForm(String[] strs, int m, int n) {
        //使用动态规划和一个额外的数组来进行保存 1 0 数量
        int[][] zeroOneCount = new int[strs.length][2]; //0下标代表的是0的个数
        for(int i = 0;i < strs.length;i++) {
            String s = strs[i];
            for(int j = 0 ;j < s.length();j++) {
                zeroOneCount[i][s.charAt(j) - '0']++;
            }
        }

        //dp[i][m][n]表示的是以m和n为参数，前面i个元素所能构成的最大值子数组
        //拥有递推公式：dp[][][] = max(1 + dp[i  - 1][m - m0][n - n0], dp[i - 1][m][n]);返回的是dp[][m][n]
        int[][][] dp = new int[strs.length][m + 1][n + 1];
        //先对第一个位置进行初始化
        for(int i = m;i >= zeroOneCount[0][0];i--) {
            for(int j = n;j >= zeroOneCount[0][1];j--) {
                dp[0][i][j] = 1;
            }
        }

        //开始进行递推
        for(int i = 1;i < strs.length;i++) {
            int m0 = zeroOneCount[i][0];
            int n0 = zeroOneCount[i][1];
            for(int j = 0;j <= m;j++) {
                for(int k = 0;k <= n;k++) {
                    if(j >= m0 && k >= n0) {
                        dp[i][j][k] = 1 + dp[i - 1][j - m0][k - n0];
                    }
                    dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k]);
                }
            }
        }

        return dp[strs.length - 1][m][n];
    }
}
