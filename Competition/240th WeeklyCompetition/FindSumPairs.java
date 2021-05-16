import java.util.*;

public class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> map;


    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        map = new HashMap<>();
        //把数据较大的nums2存入map里面
        for(int num : nums2) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
    }
    
    public void add(int index, int val) {
        map.put(nums2[index], map.getOrDefault(nums2[index], 1) - 1);
        nums2[index] += val;
        map.put(nums2[index], map.getOrDefault(nums2[index], 0) + 1);
    }
    
    public int count(int tot) {
        int count = 0;
        for(int num : nums1) {
            count += map.getOrDefault(tot - num, 0);
        }
        return count;
    }
}
