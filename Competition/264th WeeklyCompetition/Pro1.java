package src;

public class Pro1 {
    public int countValidWords(String sentence) {
        String[] strs = sentence.split(" ");
        int ans = 0;
        for (String s : strs) {
            if (isToken(s)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean isToken(String s) {
        if (s.length() == 0) {
            return false;
        }

        // 检查是否有标点符号
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == ',' || s.charAt(i) == '.' || s.charAt(i) == '!') {
                return false;
            }
        }

        // 检查是否有数字
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ('0' <= ch && ch <= '9') {
                return false;
            }
        }

        // 检查是否有-
        int sub = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '-') {
                if (sub != -1) {
                    return false;
                }
                sub = i;
            }
        }

        if (sub == -1) {
            return true;
        }
        // 检查-左右两边是否存在小写字母
        boolean flag = false;
        for (int i = 0; i < sub; i++) {
            char ch = s.charAt(i);
            if ('a' <= ch && ch <= 'z') {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return false;
        }
        flag = false;
        for (int i = sub + 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ('a' <= ch && ch <= 'z') {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
