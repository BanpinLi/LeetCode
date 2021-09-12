import java.util.*;

public class Pro2 {
    public long interchangeableRectangles(int[][] rectangles) {
        long ans = 0;

        Map<String, Integer> map = new HashMap<>();
        String[] keys = new String[rectangles.length];
        for (int i = 0; i < rectangles.length; i++) {
            int gcd = gcd(rectangles[i][0], rectangles[i][1]);
            keys[i] = rectangles[i][0] / gcd + "/" + rectangles[i][1] / gcd;
            map.put(keys[i], map.getOrDefault(keys[i], 0) + 1);
        }

        for (String key : keys) {
            map.put(key, map.getOrDefault(key, 1) - 1);
            ans += map.get(key);
        }

        return ans;
    }

    // 返回最大公约数
    private int gcd(int a, int b) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        while (a != 0) {
            int temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }
}
