package src;

public class SolutionTable {
    int[] st;
    int[] tt;

    public String minWindow(String s, String t) {
        // 在这里，使用table来代替map，看看是否会达到优化的效果，会将速度提升为原来的2倍
        // table的大小是大小写字母的个数，52
        st = new int[52];
        tt = new int[52];

        // 先初始化tt
        for (int i = 0; i < t.length(); i++) {
            // 先得到字符对应的坐标
            int index = t.charAt(i) > 'Z' ? t.charAt(i) - 'a' + 26 : t.charAt(i) - 'A';
            tt[index]++;
        }

        int start, end, left, right;
        start = end = -1;
        left = right = 0;

        // 同样先找到第一个最大序列
        while (right < s.length()) {
            int index = s.charAt(right) > 'Z' ? s.charAt(right) - 'a' + 26 : s.charAt(right) - 'A';
            if (tt[index] != 0) {
                st[index]++;
                if (check()) {
                    st[index]--;
                    break;
                }
            }
            ++right;
        }
        if (right == s.length())
            return "";

        // 能够运行到这下面的循环说明一定找到了一个满足条件的序列，最终的end一定不会是-1

        while (right < s.length()) {
            while (right < s.length()) {
                int index = s.charAt(right) > 'Z' ? s.charAt(right) - 'a' + 26 : s.charAt(right) - 'A';
                if (tt[index] != 0) {
                    st[index]++;
                    if (st[index] == tt[index]) {
                        break;
                    }
                }
                ++right;
            }
            if (right == s.length())
                break;
            // 运行到这里证明是break出来的，所以必定找到了一个满足条件的序列
            // 所以后续的left移动一定不会超出范围之外
            while (left < s.length()) {
                int index = s.charAt(left) > 'Z' ? s.charAt(left) - 'a' + 26 : s.charAt(left) - 'A';
                if (tt[index] != 0) {
                    st[index]--;
                    if (st[index] < tt[index]) {
                        break;
                    }
                }
                ++left;
            }

            // 更新start和end
            if (end == -1 || right - left < end - start) {
                start = left;
                end = right;
            }

            // 能够运行到这一步必须要满足的条件是left和right必定不为s.len，并且找到了一个满足条件的序列
            ++left;
            ++right;
        }

        return s.substring(start, end + 1);
    }

    private boolean check() {
        for (int i = 0; i < tt.length; i++) {
            if (st[i] < tt[i]) {
                return false;
            }
        }
        return true;
    }
}
