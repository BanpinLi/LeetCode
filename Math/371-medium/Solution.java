package src;

class Solution {
    public int getSum(int a, int b) {
        // 将a和b进行相加，考虑两种加法，只有进位的加法和没有进位的加法

        // 递归
        // if (b == 0) {
        // return a;
        // }
        // return getSum(a ^ b, (a & b) << 1);

        // 迭代
        while (b != 0) {
            int temp = a;
            a = a ^ b;
            b = (temp & b) << 1;
        }
        return a;
    }
}
