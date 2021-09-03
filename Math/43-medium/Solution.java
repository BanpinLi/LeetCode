class Solution {
    // 写两个函数multiplyOne和Add，分别表示将一个字符串×一位数和两个字符串相加
    public String multiply(String num1, String num2) {
        // 处理特殊情况，就是乘数出现了0，直接返回0
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int len2 = num2.length();
        String ans = multiplyOne(num1, num2.charAt(len2 - 1) - '0', 0);
        for (int i = len2 - 2; i >= 0; i--) {
            ans = addString(ans, multiplyOne(num1, num2.charAt(i) - '0', len2 - i - 1));
        }
        return ans;
    }

    // 表示将num和ch相乘，并在后面添加times个零
    private String multiplyOne(String num, int mp, int times) {
        // //处理特殊情况，那就是mp为0，直接返回"0"，不然就会返回一串0
        // //但是在主函数处理了之后，这个地方就没用了
        // if(mp == 0) {
        // return "0";
        // }

        // 作模拟乘法，用一个carry表示惩罚的进位，使用StringBuillder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(0);
        }
        int carry = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            int n = num.charAt(i) - '0';
            sb.append((n * mp + carry) % 10);
            carry = (n * mp + carry) / 10;
        }
        if (carry != 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }

    private String addString(String num1, String num2) {
        // 同样是模拟加法，使用carry来记录进位
        // 同时两个串长度是大概率不一致的，需要处理，将短的字符串补零到同样长度
        int len1 = num1.length();
        int len2 = num2.length();
        int deltLen = Math.abs(len1 - len2);
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < deltLen; i++) {
            temp.append(0);
        }
        if (len1 > len2) {
            num2 = temp.append(num2).toString();
        } else {
            num1 = temp.append(num1).toString();
        }

        int carry = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = Math.max(len1, len2) - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0';
            int b = num2.charAt(i) - '0';
            sb.append((a + b + carry) % 10);
            carry = (a + b + carry) / 10;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}