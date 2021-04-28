public class Solution_Common {
    public static void main(String[] args) {
        System.out.println(judgeSquareSum(4));
    }

    public static boolean judgeSquareSum(int c) {
        //使用普通的方法，而不是使用set，因为set的常数查找比较消耗时间
        int n = (int)Math.sqrt(c);
        for(int i = 0; i <= n; i++) {
            int num = (int)Math.sqrt(c - i * i);
            if(i * i + num * num == c) {
                return true;
            }
        }
        return false;
    }
}