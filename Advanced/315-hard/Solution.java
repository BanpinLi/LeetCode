package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    // 树状数组，以长度为10的树状数组为例
    // ======== 8
    // ==== 4
    // == == == 2 6 10
    // = = = = = 1 3 5 7 9
    // 查询是从i位置开始向左上方求和：例如得到prefix[7] -> c[7] + c[6] + c[4]
    // 更新是从i位置开始向上方更新：例如更新f[5] -> c[5] c[6] c[8]

    private int[] org; // 树状数组的原数组
    private int[] tree; // 树状数组的维护数组

    public List<Integer> countSmaller(int[] nums) {
        // 利用树状数组来进行求解
        init(nums);
        List<Integer> ans = new ArrayList<>(nums.length);
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = getIndex(nums[i]);
            update(index, 1);
            ans.add(query(index - 1));
        }
        Collections.reverse(ans);
        return ans;
    }

    // 初始化树状数组这个数据结构
    private void init(int[] nums) {
        org = discrete(nums);
        tree = new int[org.length];
    }

    // 对树状数组的i位置进行更新
    private void update(int i, int val) {
        i++;
        while (i <= tree.length) {
            tree[i - 1] += val;
            i += lowBit(i);
        }
    }

    // 获得lowBit
    private int lowBit(int x) {
        return x & (-x);
    }

    // 获得下标i位置的前缀和
    private int query(int i) {
        i++;
        int res = 0;
        while (i > 0) {
            res += tree[i - 1];
            i -= lowBit(i);
        }
        return res;
    }

    // 将nums进行离散，返回离散化之后数组
    private int[] discrete(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int[] ret = new int[set.size()];
        int offset = 0;
        for (int num : set) {
            ret[offset++] = num;
        }
        Arrays.sort(ret);
        return ret;
    }

    // 在org数组中获得target位置的下标
    private int getIndex(int target) {
        // 使用的是二分查找
        int left = 0;
        int right = org.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (org[mid] > target) {
                right = mid - 1;
            } else if (org[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
