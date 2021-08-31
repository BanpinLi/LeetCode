import java.util.*;

class Solution {

    // 求得w[i]以及之前的元素和
    private int[] w;
    private int len;
    private int sum;
    Random random;

    public Solution(int[] w) {
        len = w.length;
        this.w = new int[len];

        // 前缀和
        for (int i = 1; i < len; i++) {
            this.w[i] = this.w[i - 1] + w[i - 1];
        }
        this.sum = w[len - 1] + this.w[len - 1];

        random = new Random();
    }

    public int pickIndex() {
        // 随机产生一个数字target，target的范围是0 - (sum - 1)
        // 当target满足：w[i] <= target < w[i + 1] 时返回i
        int target = random.nextInt(sum);

        // 使用二分查找
        // 首先检查最末尾的元素
        if (target >= w[len - 1]) {
            return len - 1;
        }

        // 二分查找的主体
        int left = 0;
        int right = len - 2;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target < w[mid]) {
                right = mid - 1;
            } else if (target >= w[mid] && target < w[mid + 1]) {
                ans = mid;
                break;
            } else {
                left = mid + 1;
            }
        }
        return ans;

        // // 使用顺序查找
        // for (int i = 0; i < len - 1; i++) {
        // if (target == w[i] || (target > w[i] && target < w[i + 1])) {
        // return i;
        // }
        // }
        // return len - 1;

    }
}
