package src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class StockPrice {
    Map<Integer, Integer> tmap;
    TreeMap<Integer, Integer> pmap;
    int latestTime;

    public StockPrice() {
        tmap = new HashMap<>();
        pmap = new TreeMap<>();
        latestTime = -1;
    }

    public void update(int timestamp, int price) {
        latestTime = Math.max(timestamp, latestTime);

        int prePrice = tmap.getOrDefault(timestamp, -1);
        int preTime = pmap.getOrDefault(prePrice, -1);
        if (preTime != -1) {
            if (preTime == 1) {
                pmap.remove(prePrice);
            } else {
                pmap.put(prePrice, preTime - 1);
            }
        }
        pmap.put(price, pmap.getOrDefault(price, 0) + 1);

        tmap.put(timestamp, price);
    }

    public int current() {
        return tmap.get(latestTime);
    }

    public int maximum() {
        return pmap.lastKey();
    }

    public int minimum() {
        return pmap.firstKey();
    }
}

class StockPrice_ {
    private Map<Integer, Integer> tmap;
    private Map<Integer, HashSet<Integer>> pmap;

    private PriorityQueue<Integer> min;
    private PriorityQueue<Integer> max;
    private PriorityQueue<Integer> time;

    public StockPrice_() {
        tmap = new HashMap<>();
        pmap = new HashMap<>();
        min = new PriorityQueue<>();
        max = new PriorityQueue<>((a, b) -> b - a);
        time = new PriorityQueue<>((a, b) -> b - a);
    }

    public void update(int timestamp, int price) {
        int prePrice = tmap.getOrDefault(timestamp, -1);
        tmap.put(timestamp, price);

        time.offer(timestamp);
        min.offer(price);
        max.offer(price);

        if (prePrice != -1) {
            HashSet<Integer> set = pmap.get(prePrice);
            if (set.contains(timestamp)) {
                set.remove(timestamp);
            }
            pmap.put(prePrice, set);
        }

        HashSet<Integer> temp = pmap.getOrDefault(price, new HashSet<>());
        temp.add(timestamp);
        pmap.put(price, temp);
    }

    public int current() {
        return tmap.get(time.peek());
    }

    public int maximum() {
        int maximum = max.peek();
        while (true) {
            HashSet<Integer> set = pmap.getOrDefault(maximum, new HashSet<>());
            if (set.size() != 0) {
                return maximum;
            } else {
                max.poll();
                maximum = max.peek();
            }
        }
    }

    public int minimum() {
        int minimum = min.peek();
        while (true) {
            HashSet<Integer> set = pmap.getOrDefault(minimum, new HashSet<>());
            if (set.size() != 0) {
                return minimum;
            } else {
                min.poll();
                minimum = min.peek();
            }
        }
    }
}