class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        //使用动态规划，递推公式为：dp[i][j] = max(dp[i - 1][j - 1] + 1(nums1[i] == nums2[j]), dp[][], dp[][])
        int[][] dp = new int[nums1.length][nums2.length];
        //对零状态进行初始化
        dp[0][0] = nums1[0] == nums2[0] ? 1 : 0;
        //对边界进行初始化
        for(int i = 1;i < nums1.length;i++) {
            if(nums1[i] == nums2[0]) {
                dp[i][0] = 1;
            } else {
                dp[i][0] =  dp[i - 1][0];
            }
        }
        for(int j = 1;j < nums2.length;j++) {
            if(nums2[j] == nums1[0]) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for(int i = 1;i < nums1.length;i++) {
            for(int j = 1;j < nums2.length;j++) {
                if(nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[nums1.length - 1][nums2.length - 1];
    }
}