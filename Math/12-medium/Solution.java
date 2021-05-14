import java.util.*;

public class Solution {
    public String intToRoman(int num) {
        //罗马数字对照：
        /**
         * IV-4, IX-9, XL-40, XC-90, CD-400, CM-900
         * I-1, V-5, X-10, L-50, C-100, D-500, M-1000
         */
        //为了查找方便，将键值对存入map里面
        /*
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");
        map.put(4, "IV");
        map.put(9, "IX");
        map.put(40, "XL");
        map.put(90, "XC");
        map.put(400, "CD");
        map.put(900, "CM");
        *///也可以不使用马匹，而是直接使用String数组来进行匹配
        int[] keyNum = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] valueString = new String[] {"M", "CM", "D", "CD", "C", 
        "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < keyNum.length;i++) {
            int count = 0;
            count = num / keyNum[i];
            for(int j = 0;j < count;j++) {
                sb.append(valueString[i]);
            }
            num %= keyNum[i];
        }

        return sb.toString();
    }
}
