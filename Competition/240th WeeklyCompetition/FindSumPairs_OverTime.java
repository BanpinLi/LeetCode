import java.util.*;

class FindSumPairs_1 {
    private Map<Integer, Integer> map;
    private int[] nums;


    public FindSumPairs_1(int[] nums1, int[] nums2) {
        //nums1使用map进行存储，方便进行查询，nums2就普通数组存储即可
        nums = nums2;
        map = new HashMap<>();
        for(int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
    }
    
    public void add(int index, int val) {
        nums[index] += val;
    }
    
    public int count(int tot) {
        int count = 0;
        for(int num : nums) {
            count += map.getOrDefault(tot - num, 0);
        }
        return count;
    }
}
