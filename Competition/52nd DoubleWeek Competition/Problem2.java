class Problem2 {
    private int[] ans;

    public int[] memLeak(int memory1, int memory2) {
        //利用递归去做
        ans = new int[3];
        dfs(1, memory1, memory2);
        return ans;
    }

    private void dfs(int second, int m1, int m2) {
        if(second > m1 && second > m2) {
            ans[0] = second;
            ans[1] = m1;
            ans[2] = m2;
            return;
        }

        if(m1 >= m2) {
            dfs(second + 1, m1 - second, m2);
        } else {
            dfs(second + 1, m1, m2 - second);
        }
    }
}
