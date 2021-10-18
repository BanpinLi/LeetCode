package src;

public class SolutionSimulate {
    public void rotate(int[] nums, int k) {
        // 模拟：
        // 1 2 3 4 5 6, k = 3
        // 1 2 1 4 5 6 -> 1 2 1 4 3 6 -> 5 2 1 4 3 6
        // 5 2 1 2 3 6 -> 5 2 1 2 3 4 -> 5 6 1 2 3 4
        k %= nums.length;
        int count = gcd(nums.length, k);
        for (int i = 0; i < count; i++) {
            int cur = i;
            int next = (cur + k) % nums.length;
            int val = nums[cur];
            do {
                int temp = val;
                val = nums[next];
                nums[next] = temp;
                cur = next;
                next = (cur + k) % nums.length;
            } while (cur != i);
        }
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
