class Solution {
    public double myPow(double x, int n) {
        // if (n == 0) {
        // return 1;
        // }

        // double ans = 1;
        // int op = n < 0 ? -1 : 1;
        // n = Math.abs(n);
        // for (int i = 0; i < n; i++) {
        // ans *= x;
        // }
        // return op == 1 ? ans : 1 / ans;

        // 上面的直接进行计算的方法遇到某些极端情况会超时，采用递归 + 折半计算，也就是快速幂算法
        if (n == 1) {
            return x;
        } else if (n == 0) {
            return 1;
        } else if (n == -1) {
            return 1 / x;
        } else {
            double num = myPow(x, n / 2);
            return myPow(x, n % 2) * num * num;
        }
    }
}