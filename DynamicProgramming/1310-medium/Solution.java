class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        //利用前缀和，dp[i]表示前面i个元素的异或和
        //为了方便查找，将dp的长度设置为 arr.length + 1
        int[] dp = new int[arr.length + 1];
        for(int i = 1;i < dp.length;i++) {
            dp[i] = dp[i - 1] ^ arr[i - 1];
        }

        int[] ans = new int[queries.length];
        for(int i = 0;i < ans.length;i++) {
            ans[i] = dp[queries[i][0]] ^ dp[queries[i][1] + 1];
        }

        return ans;
    }
}