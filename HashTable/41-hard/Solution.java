class Solution {
    public int firstMissingPositive(int[] nums) {
        // //原地模拟哈希表
        // //将所有的负数全都变成 N + 1，然后遍历数组，绝对值在 1 - len
        // 之间的，使得nums对应位置取反，然后检查数组，第一个为负数的位置就是答案，否则返回 len + 1

        // //预处理
        // int len = nums.length;
        // for(int i = 0;i < nums.length;i++) {
        // if(nums[i] <= 0) {
        // nums[i] = len + 1;
        // }
        // }

        // //遍历数组并对应位置取绝对值负数
        // for(int i = 0;i < len;i++) {
        // int sub = Math.abs(nums[i]);
        // if(sub >= 1 && sub <= len) {
        // nums[sub - 1] = -Math.abs(nums[sub - 1]);
        // }
        // }

        // //遍历数组找到第一个非负数
        // for(int i = 0;i < len;i++) {
        // if(nums[i] > 0) {
        // return i + 1;
        // }
        // }

        // return len + 1;

        // 利用交换来保留数据
        // 当一个位置的值在 1 - len 的时候，让这个和对应位置的值进行交换，然后继续判断这个位置，重复
        // 当交换的两个位置相同的时候就会陷入死循环：[2, 2]这个数组就会陷入死循环
        // 避免死循环，当要交换的目标值相等就直接跳过
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int sub = nums[i];
            while (sub >= 1 && sub <= len && nums[i] != nums[sub - 1] && i != sub - 1) {
                int temp = nums[sub - 1];
                nums[sub - 1] = nums[i];
                nums[i] = temp;
                sub = nums[i];
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] - 1 != i) {
                return i + 1;
            }
        }

        return len + 1;
    }
}
