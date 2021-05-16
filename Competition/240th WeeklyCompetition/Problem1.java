public class Problem1 {
    public int subsetXORSum(int[] nums) {
        //使用回溯算法
        return dfs(0, nums, 0);
    }

    private int dfs(int depth, int[] nums, int xor) {
        if(depth == nums.length) {
            return xor;
        }

        //两种选择，进行异或或者不进行异或
        xor ^= nums[depth];
        int temp = dfs(depth + 1, nums, xor);
        xor ^= nums[depth];

        temp += dfs(depth + 1, nums, xor);

        return temp;
    }
}
