class Solution {
    public String shortestPalindrome(String s) {
        // 暴力的解法，对每一位到起点是否是回文串进行判断
        // if (s.length() == 0) {
        // return s;
        // }

        // int index = 0;
        // for (int i = 1; i < s.length(); i++) {
        // if (isPalindrome(s, 0, i)) {
        // index = i;
        // }
        // }

        // StringBuilder sb = new StringBuilder();
        // for (int i = s.length() - 1; i > index; i--) {
        // sb.append(s.charAt(i));
        // }
        // return sb.toString() + s;

        // 暴力解法会超时，采用KMP算法来进行求解
        // 使用KMP算法先求next数组
        if (s.length() == 0) {
            return s;
        }

        int[] next = new int[s.length()];
        next[0] = -1;
        int left = 0;
        int right = 1;
        while (right < s.length()) {
            if (s.charAt(right) == s.charAt(left)) {
                next[right] = left;
                left++;
                right++;
            } else {
                if (left == 0) {
                    next[right] = -1;
                    left++;
                    right++;
                } else {
                    left = next[left - 1] + 1;
                }
            }
        }

        // 得到s的反向序列rev
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        String rev = sb.toString();

        // 表示s去匹配rev的时候，最多匹配到了什么位置
        int index = 0;
        for (int i = 0; i < rev.length(); i++) {
            if (rev.charAt(i) == s.charAt(index)) {
                index++;
            } else {
                while (index != 0) {
                    index = next[index - 1] + 1;
                    if (rev.charAt(i) == s.charAt(index)) {
                        index++;
                        break;
                    }
                }
            }
        }
        // 最后出来的index是匹配到的字符串的后一个位置
        return new StringBuilder(s.substring(index)).reverse().toString() + s;
    }
}