package src;

import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Character, Integer> sMap;
    Map<Character, Integer> tMap;

    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        // 最直观的想法是，使用两个map，一个存t，一个存当前的s
        // 再一次循环中，首先right移动找到满足条件的大序列
        // 然后left移动，找到小序列，更新start和end
        // 处理未找到的情况，也就是right越界了，代表当前没有找到，返回上一次的
        sMap = new HashMap<>();
        tMap = new HashMap<>();

        // 存放t
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        int start, end, left, right;
        start = end = -1;
        left = right = 0;

        // 找到第一个最大序列
        while (right < s.length()) {
            if (tMap.containsKey(s.charAt(right))) {
                sMap.put(s.charAt(right), sMap.getOrDefault(s.charAt(right), 0) + 1);
                if (check()) {
                    break;
                }
            }
            ++right;
        }
        if (right == s.length()) {
            return "";
        }
        sMap.put(s.charAt(right), sMap.get(s.charAt(right)) - 1);

        while (right < s.length()) {
            // 找到大序列
            while (right < s.length()) {
                if (tMap.containsKey(s.charAt(right))) {
                    int sVal = sMap.getOrDefault(s.charAt(right), 0);
                    sMap.put(s.charAt(right), sVal + 1);
                    // if (check()) {
                    // break;
                    // }
                    if (tMap.get(s.charAt(right)) == sVal + 1) {
                        break;
                    }
                }
                ++right;
            }
            if (right == s.length()) {
                break;
            }

            // 找到小序列，left刚好在小序列的右边一个位置
            while (left < s.length()) {
                if (tMap.containsKey(s.charAt(left))) {
                    int sVal = sMap.getOrDefault(s.charAt(left), 0);
                    sMap.put(s.charAt(left), sVal - 1);
                    // if (!check()) {
                    // break;
                    // }
                    if (tMap.get(s.charAt(left)) > sVal - 1) {
                        break;
                    }
                }
                ++left;
            }

            if (end == -1 || right - left < end - start) {
                start = left;
                end = right;
            }
            ++left;
            ++right;
        }
        return end == -1 ? "" : s.substring(start, end + 1);
    }

    // 检查map的匹配情况
    private boolean check() {
        if (sMap.size() != tMap.size()) {
            return false;
        }

        for (char key : sMap.keySet()) {
            int sVal = sMap.get(key);
            int tVal = tMap.get(key);
            if (sVal < tVal) {
                return false;
            }
        }

        return true;
    }
}
