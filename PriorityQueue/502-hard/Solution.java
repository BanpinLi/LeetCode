import java.util.*;

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // 将capital按照升序排序，然后从左到右检查其值大小，直到不满足w条件
        // 检查capital的时候将profits放入一个大根堆里面，从堆里面取出一个数据就再次检查capital

        // 为了让capital排序之后还能和profits对应，使用二维数组来进行重构
        // nums[i][0]表示capital
        int[][] nums = new int[capital.length][2];
        for (int i = 0; i < capital.length; i++) {
            nums[i][0] = capital[i];
            nums[i][1] = profits[i];
        }

        Arrays.sort(nums, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);

        // 指向capital
        int sub = 0;

        // 每次进行循环都要取出一个数，直到不能取或者其他条件不满足
        for (int i = 0; i < k; i++) {
            while (sub < capital.length && nums[sub][0] <= w) {
                queue.offer(nums[sub][1]);
                sub++;
            }
            // 堆空了，取不出来，启动资金也不够下一轮了，直接退出循环
            if (queue.isEmpty()) {
                break;
            }
            w += queue.poll();
        }

        return w;
    }
}