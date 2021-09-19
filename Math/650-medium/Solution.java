package src;

class Solution {
    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        int factor = getMinFactor(n);
        // 判断是否能够被整除
        if (factor == 1) {
            return n;
        } else {
            return minSteps(n / factor) + factor;
        }
    }

    private int getMinFactor(int n) {
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                return i;
            }
        }
        return 1;
    }
}
