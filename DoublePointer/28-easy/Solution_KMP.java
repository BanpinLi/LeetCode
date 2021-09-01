public class Solution_KMP {
    public int strStr(String haystack, String needle) {
        // 使用KMP算法来解决字符串匹配问题

        // 首先排除掉needle为空字符串的情况
        if (needle.length() == 0) {
            return 0;
        }

        // 构造needle数组的回溯数组，next，构造next数组其实就是自相匹配的过程
        // next[i]表示的是0 - i的最长前缀和后缀相等时，前缀后一个位置
        int[] next = new int[needle.length()];
        int front = 1;
        int back = 0;
        for (; front < needle.length();) {
            // 每次都在循环的开始处判断出属于匹配的哪一种情况
            if (needle.charAt(front) == needle.charAt(back)) {
                next[front] = back + 1;
                front++;
                back++;
            } else {
                if (back == 0) {
                    next[front] = 0;
                    front++;
                } else {
                    // 让下一轮front和back = 0进行匹配
                    back = next[back - 1];
                }
            }
        }

        // 进行两个字符串匹配
        int point = 0; // 表示needle的位置
        for (int i = 0; i < haystack.length();) {
            if (haystack.charAt(i) == needle.charAt(point)) {
                // 此时的字符匹配成功了，让needle和haystack指针后移
                i++;
                point++;
            } else {
                // 此时没有匹配成功
                // 如果needle指针没有指向开始位置，就将指针回溯，等待下一次进行匹配，否则移动i指针
                if (point != 0) {
                    point = next[point - 1];
                } else {
                    i++;
                }
            }
            if (point == needle.length()) {
                return i - point;
            }
        }
        return -1;
    }
}
