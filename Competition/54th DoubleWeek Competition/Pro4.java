import java.util.*;

public class Pro4 {
    public int minOperationsToFlip(String expression) {
        //写一个递归，递归的终点就是长度长度为1，那么就直接返回1
        if(expression.length() == 1) {
            return 1;
        }
        
        //将一个字符串按照规律拆分成两部分，然后对六种情况进行组合
        int leftCount = 0; //表示的是左括号的数目
        for(int i = 0;i < expression.length();i++) {
            char ch = expression.charAt(i);
            if(ch == '(') {
                leftCount++;
            } else if((ch == '&' || ch == '|') && leftCount == 0) {
                //分为六种情况
                String leftString = expression.substring(0, i);
                String rightString = expression.substring(i + 1);
                int leftValue = theValueOfString(leftString);
                int rightValue = theValueOfString(rightString);
                if(leftValue + rightValue == 2) {
                    if(ch == '|') {
                        return 1 + Math.min(minOperationsToFlip(leftString), minOperationsToFlip(rightString));
                    } else {
                        return Math.min(minOperationsToFlip(leftString), minOperationsToFlip(rightString));
                    }
                } else if(leftValue + rightValue == 1) {
                    return 1;
                } else {
                    if(ch == '|') {
                        return Math.min(minOperationsToFlip(leftString), minOperationsToFlip(rightString));
                    } else {
                        return 1 + Math.min(minOperationsToFlip(leftString), minOperationsToFlip(rightString));
                    }
                }
            } else if(ch == ')') {
                leftCount--;
            }
        }

        //如果在前面没有进行返回，那么就返回首尾
        return minOperationsToFlip(expression.substring(1, expression.length() - 1));
    }

    //给定一个字符串，求出这个字符串所代表的值
    private int theValueOfString(String s) {
        //采用经典的模拟算法，模拟计算器的计算过程
        Stack<Character> stack = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        for(int i = 0;i < s.length();i++) {
            if(stack.isEmpty()) {
                if(s.charAt(i) == '0' || s.charAt(i) == '1') {
                    nums.add(s.charAt(i) - '0');
                } else {
                    stack.add(s.charAt(i));
                }
            } else {
                if(s.charAt(i) == ')') {
                    while(stack.peek() != '(') {
                        int left = nums.pop();
                        int right = nums.pop();
                        if(stack.pop() == '&') {  
                            nums.add(left & right);
                        } else {
                            nums.add(left | right);
                        }
                    }
                    stack.pop();
                } else if(s.charAt(i) == '1' || s.charAt(i) == '0') {
                    nums.add(s.charAt(i) - '0');
                } else {
                    stack.add(s.charAt(i));
                }
            }
        }
        while(!stack.isEmpty()) {
            int left = nums.pop();
            int right = nums.pop();
            if(stack.pop() == '&') {
                nums.add(left & right);
            } else {
                nums.add(left | right);
            }
        }
        return nums.pop();
    }
}
