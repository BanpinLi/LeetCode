public class Solution_DynamicProgramming {
    //一道特别困难的动态规划题目，细节处理比较耗费精力
    private final int INF = Integer.MAX_VALUE / 2;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        /*动态规划：dp[i][j][k]，表示第i个房子，颜色为j，属于第k个街区
        分为两种情况：第i个房子已经涂了颜色，和没有涂颜色
        涂了颜色：
            j和本来涂的颜色必须是相同的，不然这个转移方程就没有了意义：dp[][][] = INF
                j和i - 1的颜色不同:那么i个房子就会形成一个新的街区：
                    dp[i][j][k] = min(dp[i-1][j0][k-1])(j0 != j)
                j和i - 1的颜色相同，那么就不会形成一个新的街区：
                    dp[i][j][k] = dp[i-1][j][k]
        当i个房子没有涂颜色的时候：
            给i涂上的颜色和i-1是一样的时候：
                dp[i][j][k] = dp[i-1][j][k]
            给i涂上的颜色是不一样的时候：
                dp[i][j][k] = min(dp[i-1][j0][k-1])
        初始化dp数组：
            dp[0][j][k]：k一定为0，否则无意义
            j的值取决于这个房子是否是涂上了颜色
                涂上了，则非颜色值无意义
                没涂上，价值为cost[0][j]
        */

        int[][][] dp = new int[m][n][target];

        //将house的初始0变为-1，方便后面的填入颜色0不会造成冲突
        for(int i = 0;i < m;i++) {
            houses[i]--;
        }

        //Arrays.fill(dp, INF);
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                for(int k = 0;k < target;k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        //这一步是初始化 i = 0 的所有的dp的值
        if(houses[0] == -1) {
            for(int j = 0;j < n;j++) {
                dp[0][j][0] = cost[0][j];
            }
        } else {
            dp[0][houses[0]][0] = 0;
        }

        for(int i = 1;i < m;i++) {
            if(houses[i] != -1) {
                //dp[i][j][k] = min(dp[i-1][j0][k-1])
                for(int j = 0;j < n;j++) {
                    for(int k = 1;k < target;k++) {
                        if(j != houses[i]) {
                            dp[i][houses[i]][k] = Math.min(dp[i][houses[i]][k], dp[i - 1][j][k - 1]);
                        }
                    }
                }
                for(int k = 0;k < target;k++) {
                    //为什么这里要使用一个min函数来取得最小值呢？在下面中的另一种情况也是一样的原因
                    //因为前面的这个状态可能已经拥有了一个确定的值，而不是原始的INF，所以直接使用就会覆盖掉
                    dp[i][houses[i]][k] = Math.min(dp[i][houses[i]][k], dp[i - 1][houses[i]][k]);
                }
                continue;
            }
            for(int j = 0;j < n;j++) {
                for(int k = 1;k < target;k++) {
                    //dp[i][j][k] = min(dp[i-1][j0][k-1])
                    for(int j0 = 0;j0 < n;j0++) {
                        if(j != j0) {
                            dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j0][k - 1] + cost[i][j]);
                        } else {
                            //为什么这里需要加一个min，同上面解释一样
                            //ijk可能已经在另外的情况下做过状态转移了，此时直接对其进行赋值会覆盖掉可能的最优解
                            dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][k] + cost[i][j]);
                        }
                    }
                }
                dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][j][0] + cost[i][j]);
            }
        }

        int ans = INF;
        for(int j = 0;j < n;j++) {
            ans = Math.min(ans, dp[m - 1][j][target - 1]);
        }
        return ans == INF ? -1 : ans;
    }
}
