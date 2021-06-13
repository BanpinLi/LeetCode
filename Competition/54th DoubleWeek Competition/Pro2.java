public class Pro2 {
    public int chalkReplacer(int[] chalk, int k) {
        //一道模拟题，按照题目的意思来就是了，不需要进行额外的操作
        //但是这样会超时，所以需要现获得chalk数组的总和，然后令k对sum取余
        //sum的大小是有可能超过int的最大值，所以需要使用long来进行记录
        long sum = 0;
        for(int i = 0;i < chalk.length;i++) {
            sum += chalk[i];
        }
        long temp = k % sum;
        k = (int)temp;
        int index = 0;
        while(chalk[index] <= k) {
            k -= chalk[index];
            index++;
        }
        return index;
    }
}
