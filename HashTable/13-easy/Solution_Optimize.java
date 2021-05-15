import java.util.*;

public class Solution_Optimize {
    Map<Character, Integer> map = new HashMap<>(){{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt(String s) {
        //直接对string进行操作会比较慢，优化一下，每一个字符都对应着相应的值，当前一个值小于后一个时
        //就将符号取反
        int frontNum = 1001;
        int curNum = 0;
        int ans = 0;
        for(int i = 0;i < s.length();i++) {
            curNum = map.get(s.charAt(i));
            ans += curNum;
            if(curNum > frontNum) {
                ans -= 2 * frontNum;
            }
            frontNum = curNum;
        }

        return ans;
    }
}
