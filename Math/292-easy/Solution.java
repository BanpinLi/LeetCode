class Solution {
    public boolean canWinNim(int n) {
        // a和b先后拿，考虑两种情况：
        // n是4的倍数的时候：a拿t个，b拿4 - t个，那么最后b最终可以拿到最后一个
        // n不是4的倍数的时候，a拿n % 4个，b拿t个，a再拿4 - t个，最后a可以拿到最后一个
        return n % 4 != 0;
    }
}