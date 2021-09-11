class Solution {
    public int findIntegers(int n) {
        // 特殊情况
        if (n == 1) {
            return 2;
        }

        // 得到n的二进制反序序列，长度为len
        int[] bits = new int[30];
        int len = 0;
        while (n > 0) {
            bits[len++] = n % 2;
            n /= 2;
        }

        // 得到斐波拉契数列
        int[] fib = new int[len];
        fib[0] = 1;
        fib[1] = 2;
        for (int i = 2; i < len; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        // 返回的答案
        int ans = 0;

        // 定义两个变量，来存储上一个bit和当前的bit
        int preBit = 0;
        int curBit = 0;
        for (int i = len - 1; i >= 0; i--) {
            curBit = bits[i];
            // 当前bit有两种情况，只有为1的时候才进行特殊处理
            if (curBit == 1) {
                // 此时取curBit为0，会有一系列满足条件的组合，由fib给出
                ans += fib[i];

                if (preBit == 1) {
                    // 连续出现了1，表示往后都无法满足条件了
                    break;
                }
            }

            if (i == 0) {
                ans++;
            }

            // 向前进一步
            preBit = curBit;
        }

        return ans;
    }
}