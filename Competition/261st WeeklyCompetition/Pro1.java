package src;

public class Pro1 {
    public int minimumMoves(String s) {
        // 转换成数组
        int[] nums = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            nums[i] = s.charAt(i) == 'X' ? 0 : 1;
        }

        int p = 0;
        int ans = 0;
        while (p < nums.length && nums[p] == 1) {
            p++;
        }

        while (p < nums.length) {
            p += 3;
            while (p < nums.length && nums[p] == 1) {
                p++;
            }
            ans++;
        }
        return ans;
    }
}
