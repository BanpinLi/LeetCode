package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private List<String> ans;
    private Map<String, Integer> map;

    public List<String> removeInvalidParentheses(String s) {
        // 由简入繁，先使用普通的回溯来回溯这25个括号，并且对于结果写一个函数判断一下，并去重
        // 由于是删除最少括号，所以需要得到最少删除几个括号
        int targetLen = s.length();
        int leftNum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftNum++;
            } else if (s.charAt(i) == ')') {
                if (leftNum == 0) {
                    targetLen--;
                } else {
                    leftNum--;
                }
            }
        }
        targetLen -= leftNum;

        ans = new ArrayList<>();
        map = new HashMap<>();
        StringBuilder path = new StringBuilder();
        dfs(path, 0, s, targetLen);

        return ans;
    }

    private void dfs(StringBuilder path, int depth, String s, int targetLen) {
        if (depth == s.length()) {
            String temp = path.toString();
            if (!map.containsKey(temp) && path.length() == targetLen && isValid(temp)) {
                ans.add(temp);
                map.put(temp, 0);
            }
            return;
        }

        path.append(s.charAt(depth));
        dfs(path, depth + 1, s, targetLen);
        path.deleteCharAt(path.length() - 1);

        if (s.charAt(depth) == '(' || s.charAt(depth) == ')') {
            dfs(path, depth + 1, s, targetLen);
        }
    }

    // 判断字符串是否是复合条件的字符串
    private boolean isValid(String s) {
        // 左括号数量
        int leftNum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftNum++;
            } else if (s.charAt(i) == ')') {
                leftNum--;
                if (leftNum < 0) {
                    return false;
                }
            }
        }

        return leftNum == 0;
    }
}
