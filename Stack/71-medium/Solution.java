package src;

import java.util.LinkedList;

class Solution {
    public String simplifyPath(String path) {
        // 使用栈来进行操作，.直接忽略，..弹栈，直到栈没有东西了，直接退出
        String[] pathes = path.split("/");
        LinkedList<String> stack = new LinkedList<>();
        for (String s : pathes) {
            if (s.equals(".") || s.equals("")) {
                continue;
            } else if (s.equals("..")) {
                if (stack.isEmpty()) {
                    continue;
                }
                stack.removeLast();
            } else {
                stack.addLast(s);
                ;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : stack) {
            sb.append("/" + s);
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}