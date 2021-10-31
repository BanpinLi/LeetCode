package src;

public class Pro3 {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        // 在某一个位置，给出跳转到距离其左右最近的|的位置

        // 在某一个位置，给出从当前位置到0的|的数目
        int[] verticalBar = new int[s.length()];
        verticalBar[0] = s.charAt(0) == '|' ? 1 : 0;
        for (int i = 1; i < s.length(); i++) {
            verticalBar[i] = verticalBar[i - 1] + (s.charAt(i) == '|' ? 1 : 0);
        }

        int[] rights = getRightBar(s);

        int[] lefts = getLeftBar(s);

        int[] ret = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int right = lefts[queries[i][1]];
            int left = rights[queries[i][0]];
            if (left >= right) {
                ret[i] = 0;
                continue;
            }

            int midBar = verticalBar[right] - verticalBar[left] - 1;
            ret[i] = right - left - 1 - midBar;
        }

        return ret;
    }

    private int[] getRightBar(String s) {
        int[] ret = new int[s.length()];
        int pointer = 0;
        int off = 0;
        while (pointer < s.length()) {
            while (pointer < s.length() && s.charAt(pointer) == '*') {
                pointer++;
            }
            while (off < s.length() && off <= pointer) {
                ret[off++] = pointer;
            }
            pointer++;
        }
        return ret;
    }

    private int[] getLeftBar(String s) {
        int[] ret = new int[s.length()];
        int pointer = s.length() - 1;
        int off = pointer;
        while (pointer >= 0) {
            while (pointer >= 0 && s.charAt(pointer) == '*') {
                pointer--;
            }
            while (off >= 0 && off >= pointer) {
                ret[off--] = pointer;
            }
            pointer--;
        }

        return ret;
    }
}
