public class Solution_DynamicProgramming {
    public boolean canCross(int[] stones) {
        //使用动态规划，状态转移方程为：dp[i][k] = dp[j][k-1] | dp[j][k] | dp[j][k+1]
        //dp[i][k]表示的是到达第i个石头，并且从上一个石头跳过来的步数是k
        //再进行状态转移的时候，是不断遍历i和j，而不是遍历k，因为k是通过i和j对应的数组来得到的
        //对方程的说明，是否能够以k步跳到i块石头，取决于是否能够以(k k-1 k+1)跳到j块石头

        //我们可以发现，i 和 i - 1 的距离如果超过了i的话，那么一定是无法到达的，在这里可以预处理一下
        for(int i = 1; i < stones.length; i++) {
            if(stones[i] - stones[i - 1] > i) {
                return false;
            }
        }

        //对于dp数组的初始化长度，k代表跳的步数，这个步数一定是小于n的
        boolean dp[][] = new boolean[stones.length][stones.length];
        
        //初始化dp数组
        //采用这种初始化的方式的话，则必须要保证stones[1] == 1，根据上面的预处理，这里的条件已经可以满足了
        dp[1][1] = dp[0][0] = true; //代表跳到第1块石头只需要0步,到达第2块石头必须使用1步
        for(int i = 2;i < stones.length;i++) {
            for(int j = 0;j < i;j++) {
                int k = stones[i] - stones[j];
                if(k > stones.length - 1) {
                    continue;
                }
                
                dp[i][k] = dp[j][k];

                //这里注意数组的越界处理
                if(k < stones.length - 1) {
                    dp[i][k] |= dp[j][k + 1];
                }
                if(k > 1) {
                    dp[i][k] |= dp[j][k - 1];
                }
            }
        }

        //循环dp[n - 1]，用以找到是否是存在true的情况
        for(boolean b : dp[stones.length - 1]) {
            if(b) {
                return true;
            }
        }
        return false;
    }
}