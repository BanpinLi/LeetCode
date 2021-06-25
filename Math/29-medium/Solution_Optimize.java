public class Solution_Optimize {
    public int divide(int dividend, int divisor) {
        //采用直接的逐个元素递增是会超时的，我们考虑倍增法
        //具体来说，每次让divisor倍增，当dividend长度不为divisor两倍的时候，结束递增，对多余部分递归
        // -17 和 -3：-17 = -3 * 4 + （-5），对 -5 按照同样方法递归，返回 (4 + 递归结果)
        //先对其进行预先处理，排除掉特殊的情况
        if(dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        //然后确定返回值的符号是什么
        int op = 0;
        if((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            op = 1;
        } else {
            op = -1;
        }
        //进行变成负数的处理
        dividend = dividend < 0 ? dividend : -dividend;
        divisor = divisor < 0 ? divisor : -divisor;
        return divideNumber(dividend, divisor) * op;
    }

    //前面进行处理了，所以参数都是非正数
    private int divideNumber(int dm, int di) {
        //递归终点，当被除数大于除数的时候就直接返回
        if(dm > di) {
            return 0;
        }

        int ans = 1;
        int temp = di;
        //while条件这样写用于避免后面 temp + temp 的溢出风险
        while(dm - temp <= temp) {
            ans += ans;
            temp += temp;
        }
        return ans + divideNumber(dm - temp, di);
    }
}
