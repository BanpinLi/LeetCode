class Solution {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        //利用前缀和，来得到前面i天的总的糖果数量，prefix[i]包括了第i天的糖果数目
        //为了防止后面的下标越界，所以多开辟一个空间
        long[] prefix = new long[candiesCount.length + 1];
        for(int i = 1;i < prefix.length;i++) {
            prefix[i] = prefix[i - 1] + candiesCount[i - 1];
        }
        boolean[] ans = new boolean[queries.length];
        for(int i = 0;i < queries.length;i++) {
            long day = queries[i][1];
            long maxNum = queries[i][2];
            int subCandi = queries[i][0];
            if((day + 1) > prefix[subCandi + 1] || (day + 1) * maxNum <= prefix[subCandi]) {
                ans[i] = false;
            } else {
                ans[i] = true;
            }
        }
        return ans;
    }
}