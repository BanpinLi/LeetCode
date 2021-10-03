package src;

import java.util.Arrays;

public class Pro2 {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int sum = mean * (n + rolls.length);
        ;
        int rsum = 0;
        for (int num : rolls) {
            rsum += num;
        }
        if ((sum - rsum) > n * 6 || (sum - rsum) < n) {
            return new int[0];
        }

        int target = sum - rsum;
        int avg = target / n;
        int r = target % n;
        int[] ans = new int[n];
        Arrays.fill(ans, avg);
        for (int i = 0; i < r; i++) {
            ans[i] += 1;
        }
        return ans;
    }
}
