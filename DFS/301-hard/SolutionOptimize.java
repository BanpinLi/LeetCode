package src;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class SolutionOptimize {
    private List<String> ans;
    private Map<String, Integer> map;

    public List<String> removeInvalidParentheses(String s) {
        // 在前一种方法中，使用的是每一个位置都进行增删，没有枝减比较耗时
        // 考虑一种方法，动态的存储当前path中的左右括号数目，达到枝减效果
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
        dfs(path, 0, 0, targetLen, s);

        return ans;
    }

    private void dfs(StringBuilder path, int depth, int leftNum, int targetLen, String s) {
        // 满足长度，没出现过，lefNum为0就添加ans
        if (depth == s.length()) {
            String temp = path.toString();
            if (leftNum == 0 && path.length() == targetLen && !map.containsKey(temp)) {
                ans.add(temp);
                map.put(temp, 0);
            }
            return;
        }

        // 枝减第一步，剩余长度不够直接返回
        if (s.length() - depth + path.length() < targetLen) {
            return;
        }

        char ch = s.charAt(depth);

        if (ch != '(' && ch != ')') {
            path.append(ch);
            dfs(path, depth + 1, leftNum, targetLen, s);
            path.deleteCharAt(path.length() - 1);
        } else {
            dfs(path, depth + 1, leftNum, targetLen, s);

            path.append(ch);
            if (ch == ')' && leftNum != 0) {
                dfs(path, depth + 1, leftNum - 1, targetLen, s);
            } else if (ch == '(') {
                dfs(path, depth + 1, leftNum + 1, targetLen, s);
            }
            path.deleteCharAt(path.length() - 1);
        }
    }
}
