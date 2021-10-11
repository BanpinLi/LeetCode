package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) {
            return new ArrayList<>();
        }

        List<String> ans = new ArrayList<>();
        Map<String, Boolean> map = new HashMap<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String subString = s.substring(i, i + 10);
            if (map.containsKey(subString)) {
                if (map.get(subString)) {
                    ans.add(subString);
                    map.put(subString, false);
                }
            } else {
                map.put(subString, true);
            }
        }
        return ans;
    }
}
