public class Pro1 {
    public String largestOddNumber(String num) {
        //从后往前遍历，找到第一个是奇数的位置
        int index;
        for(index = num.length() - 1;index >= 0;index--) {
            int ch = num.charAt(index) - '0';
            if(ch % 2 == 1) {
                break;
            }
        }
        return num.substring(0, index + 1);
    }
}
