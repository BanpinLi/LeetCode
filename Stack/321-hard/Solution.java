package src;

import java.util.LinkedList;

public class Solution {
    private int[] ans;

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        ans = new int[k];
        int len1, len2;
        len1 = 0;
        len2 = k;
        for (int i = 0; i <= k; i++) {
            if (len1 <= nums1.length && len2 <= nums2.length) {
                int[] subs1 = findMaxSubsquenceOfK(nums1, len1);
                int[] subs2 = findMaxSubsquenceOfK(nums2, len2);
                updateMaxAns(merge(subs1, subs2));
            }
            len1++;
            len2--;
        }

        return ans;
    }

    // 找到一个数组中长度为k的子序列，使得其为最大的子序列
    private int[] findMaxSubsquenceOfK(int[] nums, int k) {
        if (nums == null || k >= nums.length) {
            return nums;
        }

        // 维护一个单调栈，首先栈需要初始化到倒数k位
        LinkedList<Integer> stack = new LinkedList<>();
        int[] ret = new int[k];
        int threshold = nums.length - k;
        for (int i = 0; i < nums.length; i++) {
            updateSingleStack(stack, nums[i]);
            if (i >= threshold) {
                ret[i - threshold] = stack.removeFirst();
            }
        }

        return ret;
    }

    // 更新这个单调栈
    private void updateSingleStack(LinkedList<Integer> stack, int val) {
        while (!stack.isEmpty() && val > stack.getLast()) {
            stack.removeLast();
        }
        stack.addLast(val);
        ;
    }

    // 将两个子序列合并，返回合并的最大值
    private int[] merge(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return nums1 == null ? nums2 : nums1;
        }

        int[] ret = new int[nums1.length + nums2.length];
        int off, off1, off2;
        off = off1 = off2 = 0;
        while (off1 < nums1.length || off2 < nums2.length) {
            // 合并算法：取当前的最大值，相等的话，就按照下一位进行比较
            // 如果下一位还是相等的话，继续重复取下一位
            int val1 = off1 < nums1.length ? nums1[off1] : Integer.MIN_VALUE;
            int val2 = off2 < nums2.length ? nums2[off2] : Integer.MIN_VALUE;
            int temp1 = val1;
            int temp2 = val2;
            if (val1 == val2) {
                int sub1 = off1 + 1;
                int sub2 = off2 + 1;
                while (temp1 == temp2) {
                    temp1 = sub1 < nums1.length ? nums1[sub1] : Integer.MIN_VALUE + 1;
                    temp2 = sub2 < nums2.length ? nums2[sub2] : Integer.MIN_VALUE;
                    sub1++;
                    sub2++;
                }
            }

            if (val1 < val2 || temp1 < temp2) {
                ret[off++] = val2;
                off2++;
            } else {
                ret[off++] = val1;
                off1++;
            }
        }

        return ret;
    }

    // 将nums和ans作比较，更新ans
    private void updateMaxAns(int[] nums) {
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] < nums[i]) {
                ans = nums;
                break;
            } else if (ans[i] > nums[i]) {
                break;
            }
        }
    }
}
