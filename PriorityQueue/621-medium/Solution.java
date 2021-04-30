import java.util.*;

//优先队列，当然使用一个数组来代替优先队列也是可行的，但是在实现上没有priorityQueue方便
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        //先使用数组来统计出每个字符所对应的出现频次，使用优先队列来将非零值下标进行存储
        //每次进行任务调度的过程其实就是堆中选出n+1个元素的过程
        //有几种情况，选完堆空了：选满了，没选满，选完堆没空：选满了，没选满
        int[] nums = new int[26];
        Arrays.fill(nums, 0);
        for(char ch : tasks) {
            nums[ch - 'A']++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> nums[b] - nums[a]);
        for(int i = 0;i < 26;i++) {
            if(nums[i] != 0) {
                pq.offer(i);
            }
        }

        int ans = 0;    //用来记录处理的任务的个数
        while(true) {
            //定义一个长度为n+1的数组来记录出队的元素，使用len来记录出队元素的个数
            int[] sub = new int[n + 1];
            int len = 0;
            while(!pq.isEmpty() && len != n + 1) {
                sub[len++] = pq.poll();    //注意这里出队的是下标
                nums[sub[len - 1]]--;  //出队一次代表被选一次，所以个数减一
            }

            //更新优先队列
            for(int i = 0;i < len;i++) {
                //对应的个数不为0，才进行入队操作
                if(nums[sub[i]] != 0) {
                    pq.offer(sub[i]);
                }
            }

            //判断队列是否为空，如果为空的话，证明所有元素都被选走了，结束循环，否则继续
            if(pq.isEmpty()) {
                ans += len;
                break;
            } else {
                ans += n + 1;
            }
        }

        return ans;
    }
}
