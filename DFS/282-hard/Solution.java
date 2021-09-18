import java.util.*;

class Solution {
    List<String> ans;

    public List<String> addOperators(String num, int target) {
        // 回溯算法，达到回溯深度判断target是否相等，确定是否加入答案
        ans = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(num, target, path, 0);
        return ans;
    }

    private void dfs(String num, int target, StringBuilder path, int depth) {
        path.append(num.charAt(depth));

        // 回溯终点，到达了最后一个字符
        if (depth == num.length() - 1) {
            if (isTarget(path, target)) {
                ans.add(path.toString());
            }
            path.deleteCharAt(path.length() - 1);
            return;
        }

        // 往path里面加一个符号
        // 加空
        dfs(num, target, path, depth + 1);

        path.append('*');
        dfs(num, target, path, depth + 1);
        path.deleteCharAt(path.length() - 1);

        path.append('-');
        dfs(num, target, path, depth + 1);
        path.deleteCharAt(path.length() - 1);

        path.append('+');
        dfs(num, target, path, depth + 1);
        path.deleteCharAt(path.length() - 1);

        // 将加进去的数字删掉
        path.deleteCharAt(path.length() - 1);
    }

    public boolean isTarget(StringBuilder s, int target) {
        // 需要判断02这种不合法的表达式，判断0的左右两边的情况
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                if (i == 0) {
                    if ('0' <= s.charAt(i + 1) && s.charAt(i + 1) <= '9') {
                        return false;
                    }
                } else {
                    if ('0' <= s.charAt(i + 1) && s.charAt(i + 1) <= '9'
                            && ('0' > s.charAt(i - 1) || s.charAt(i - 1) > '9')) {
                        return false;
                    }
                }
            }
        }

        // 将字符串看成是首部为+的一个字符串
        // 在进行入栈的操作的时候，是根据入栈元素和其前面的op来进行决定的
        Deque<Long> stack = new LinkedList<>();
        char op = '+';
        long num = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                continue;
            }
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            } else {
                if (op == '+') {
                    stack.push(num);
                } else if (op == '-') {
                    stack.push(-num);
                } else if (op == '*') {
                    stack.push(stack.pop() * num);
                }
                num = 0;
                op = ch;
            }
        }
        long sum = 0;
        if (op == '+') {
            sum = num;
        } else if (op == '-') {
            sum = -num;
        } else if (op == '*') {
            sum = stack.pop() * num;
        }
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum == target;
    }
}