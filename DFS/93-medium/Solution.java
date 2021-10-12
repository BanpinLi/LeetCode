package src;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<String> ans;
    // 如果不考虑效率的话，逻辑非常清晰
    // 当对过程进行了一定的枝减之后，逻辑变得不太清晰，特别是对于递归出口和加"."的理解

    public List<String> restoreIpAddresses(String s) {
        // 长度超过12的时候，必然不对
        if (s.length() > 12) {
            return new ArrayList<>();
        }

        // 使用回溯算法，在最后判断一下得到的字符串是否是满足条件的
        ans = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(path, 0, 0, s, 0);
        return ans;
    }

    // 新增一个num，表示从上一个'.'到这里的数字的大小，用来提前判断是否满足条件
    private void dfs(StringBuilder path, int depth, int countDot, String s, int num) {
        if (depth == s.length()) {
            if (countDot == 4) {
                // 每一个字符串，都有4个点，需要去除最后一个点
                ans.add(new String(path.substring(0, path.length() - 1)));
            }
            return;
        }

        char ch = s.charAt(depth);
        num = num * 10 + ch - '0';
        // 考虑一些需要提前返回的情况，作为枝减调优
        if ((4 - countDot) * 3 < s.length() - depth || num > 255) {
            return;
        }

        // 处理前导0的情况，当一个num为0的时候，并且ch也为0，那么将ch加入进去就必须加入'.'
        path.append(ch);
        if (!(num == 0 && ch == '0')) {
            dfs(path, depth + 1, countDot, s, num);
        }

        path.append('.');
        dfs(path, depth + 1, countDot + 1, s, 0);
        path.deleteCharAt(path.length() - 1);

        path.deleteCharAt(path.length() - 1);
    }

    // 做进一步优化，将这个判断一个字符串的是否是符合条件的过程直接放在dfs的过程中
    // 给一个字符串，里面有3个"."，并且每一个部分长度都不超过3
    private boolean isCorrectIP(String ip) {
        String[] s = ip.split("\\.");
        for (String bit : s) {
            if (bit.length() != 1 && bit.charAt(0) == '0') {
                return false;
            }
            if (Integer.parseInt(bit) > 255) {
                return false;
            }
        }
        return true;
    }
}