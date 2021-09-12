import java.util.*;

class Solution {
    public boolean checkValidString(String s) {
        // bracket
        // //先用Stack做一遍
        // Deque<Integer> stackBracket = new LinkedList<>();
        // Deque<Integer> stackStar = new LinkedList<>();

        // for(int i = 0;i < s.length();i++) {
        // if(s.charAt(i) == '(') {
        // stackBracket.push(i);
        // } else if(s.charAt(i) == ')') {
        // if(stackBracket.isEmpty()) {
        // if(stackStar.isEmpty()) {
        // return false;
        // }
        // stackStar.pop();
        // } else {
        // stackBracket.pop();
        // }
        // } else {
        // stackStar.push(i);
        // }
        // }

        // while(!stackBracket.isEmpty() && !stackStar.isEmpty()) {
        // int leftBracketIndex = stackBracket.pop();
        // int starIndex = stackStar.pop();
        // if(leftBracketIndex > starIndex) {
        // return false;
        // }
        // }
        // return stackBracket.isEmpty();

        // 用其他方法做一遍，依次遍历，维护左括号的上下范围
        int low = 0;
        int high = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                low++;
                high++;
            } else if (ch == ')') {
                low = Math.max(0, low - 1);
                high--;
                if (high < 0) {
                    return false;
                }
            } else {
                high++;
                low = Math.max(0, low - 1);
            }
        }
        return low == 0;
    }
}