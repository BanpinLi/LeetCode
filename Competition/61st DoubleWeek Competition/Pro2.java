package src;

import java.util.*;

public class Pro2 {
    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 == 1) {
            return new int[0];
        }

        Arrays.sort(changed);
        int[] ans = new int[changed.length / 2];
        int size = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : changed) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : changed) {
            if (map.getOrDefault(num, 0) > 0) {
                map.put(num, map.getOrDefault(num, 1) - 1);
                if (map.getOrDefault(num * 2, 0) > 0) {
                    map.put(num * 2, map.getOrDefault(num * 2, 1) - 1);
                    ans[size++] = num;
                } else {
                    break;
                }
            }
        }
        return size == changed.length / 2 ? ans : new int[0];
    }
}
