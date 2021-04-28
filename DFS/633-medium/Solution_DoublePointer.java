public class Solution_DoublePointer {
    public static void main(String[] args) {
        System.out.println(judgeSquareSum(4));
    }

    public static boolean judgeSquareSum(int c) {
        //左右指针，参考三数之和
        //左右指针所指向的数如果大于结果，动右指针，小于结果动左指针
        int left = 0;
        int right = (int)Math.sqrt(c);

        while(left <= right) {
            if(left * left + right * right < c) {
                left++;
            } else if(left * left + right * right > c) {
                right--;
            } else {
                return true;
            }
        }
        return false;
    }
}
