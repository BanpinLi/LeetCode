public class Solution {
    //捏麻麻的，超时了，测试数据的量级达到了10^9，很容易就超时了
    public int divide(int dividend, int divisor) {
        //考虑几种结果越界的极端情况：最小值除以-1，那么越界了
        //计算的时候为了方便，先确定返回值的符号，然后将两个数字全部转换成对应的负数
        //特殊情况考虑
        if(dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int op = 0; //用来记录返回值的符号
        if((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            op = 1;
        } else {
            op = -1;
        }
        dividend = dividend < 0 ? dividend : -dividend;
        divisor = divisor < 0 ? divisor : -divisor;
        int ans = 0;
        while(dividend <= divisor) {
            ans++;
            dividend -= divisor;
        }
        return ans * op;
    }
}
