import java.util.*;

public class Solution {
    public int singleNumber(int[] nums) {
        //常规解法，使用map来存储每个元素的出现次数，然后遍历map
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for(int num : map.keySet()) {
            if(map.get(num) == 1) {
                return num;
            }
        }
        return 0;
    }
}
