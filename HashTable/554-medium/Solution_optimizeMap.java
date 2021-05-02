import java.util.*;

public class Solution_optimizeMap {
    public int leastBricks(List<List<Integer>> wall) {
        //总而言之，统计所有边界数的出现次数
        //前面的思路都是将所有行的前缀和存储到set里面，最后通过查找set是否包含目的边界来得到答案
        //但是set的存储和查找过程都是比较消耗时间的，所以使用map来记录所有的边界出现的次数

        int ans = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0;i < wall.size();i++) {
            int prefix = 0;
            for(int j = 0;j < wall.get(i).size() - 1;j++) {
                prefix += wall.get(i).get(j);
                int count = map.getOrDefault(prefix, 0) + 1;
                ans = Math.max(ans, count);
                map.put(prefix, count);
            }
        }

        /*
        //遍历map，找到最大值，进行处理返回
        for(int key : map.keySet()) {
            ans = Math.max(ans, map.get(key));
        }
        */

        return wall.size() - ans;
    }
}
