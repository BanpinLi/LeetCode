package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pro1 {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        int[] table = new int[102];
        getOrder(nums1, table);
        getOrder(nums2, table);
        getOrder(nums3, table);
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] >= 2) {
                ret.add(i);
            }
        }
        return ret;
    }

    private void getOrder(int[] nums, int[] table) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : set) {
            table[num]++;
        }
    }
}
