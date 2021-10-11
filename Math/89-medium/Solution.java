package src;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> grayCode(int n) {
        // // 有规律可循
        // // 初始为0，当到达了2^n的时候，就为2^n | 2^n - i
        // List<Integer> ans = new ArrayList<>((int) Math.pow(2, n));
        // ans.add(0);
        // int towPow = 1;
        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < towPow; j++) {
        // ans.add(0);
        // ans.set(towPow + j, towPow | ans.get(towPow - j - 1));
        // }
        // towPow <<= 1;
        // }

        // return ans;

        // 格雷码出处：G(i) = i ^ i / 2;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++)
            ans.add(i ^ i >> 1);
        return ans;
    }
}
