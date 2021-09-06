class Solution {
    public int jump(int[] nums) {
        // //是用动态规划进行解决
        // //dp[i]表示跳转到这一步所需要的最少步数，转移方程给出来
        // //dp[i] = dp[k] + 1；需要nums[k] >= i - k
        // int[] dp = new int[nums.length];
        // for(int i = 1;i < nums.length;i++) {
        // dp[i] = nums.length;
        // for(int j = 0;j < i;j++) {
        // if(nums[j] >= i - j) {
        // dp[i] = Math.min(dp[i], dp[j] + 1);
        // }
        // }
        // }
        // return dp[nums.length - 1];

        // 动态规划的时间和空间复杂度都太高了，使用贪心算法来降低复杂度
        // 每次在一个格子进行跳跃的时候，都有多种选择，每一种选择对应的下一次跳跃都不同
        // 选择两次跳跃长度最长的情况作为下一次的跳跃点，并将跳跃次数加一
        // 为什么算法能够成功，因为如果选择了最长的距离的话，那么在这个距离范围内的所有情况都可以达到，不会出现“跳过了”的情况

        // 跳跃的次数
        int ans = 0;
        // 一次跳跃能够到达的最远距离
        int distance = 0;
        // 记录这次跳跃的开始位置
        int start = 0;
        // 记录下一次进行跳跃的位置
        int end = 0;
        // 当下一次跳跃的位置到达或者超过数组的末尾的话，就一直执行程序
        while (end < nums.length - 1) {
            distance = 0;
            for (int i = 1; i <= nums[start]; i++) {
                if (i + start >= nums.length - 1) {
                    end = nums.length - 1;
                    break;
                }
                if (i + nums[i + start] > distance) {
                    distance = i + nums[i + start];
                    end = i + start;
                }
            }
            start = end;
            ans++;
        }
        return ans;
    }
}