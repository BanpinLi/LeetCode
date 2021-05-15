import java.util.*;

class Solution {
    public int romanToInt(String s) {
        //将罗马数字和阿拉伯数字对应起来
        //遍历罗马数字，然后将对应的阿拉伯数字存入ans
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        int ans = 0, i;
        for(i = 0;i < s.length() - 1;i++) {
            if(map.containsKey(s.substring(i, i + 2))) {
                ans += map.get(s.subSequence(i, i + 2));
                i++;
            } else {
                ans += map.get(s.substring(i, i + 1));
            }
        }
        if(i != s.length()) {
            ans += map.get(s.substring(i, i + 1));
        }
        return ans;
    }
}