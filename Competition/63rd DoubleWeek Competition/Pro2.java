package src;

public class Pro2 {
    public boolean winnerOfGame(String colors) {
        // 统计连续的A和B的个数，超过3才进行统计
        int aCount = countAOrB(colors, 'A');
        int bCOunt = countAOrB(colors, 'B');
        return aCount - bCOunt > 0;
    }

    private int countAOrB(String s, char ch) {
        int ret = 0;
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) == ch && s.charAt(i - 1) == ch && s.charAt(i - 2) == ch) {
                ret++;
            }
        }
        return ret;
    }
}
