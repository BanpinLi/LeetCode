package src;

import java.util.Deque;
import java.util.LinkedList;

class SolutionStack {
    public int trap(int[] height) {
        // 单调栈，存储的是严格递减的数组的下标，注意是下标
        // 当大于栈顶的元素入栈的时候，弹出栈顶元素，计算体积
        // 体积的计算是按照层数来进行计算的，弹出的栈顶的高度，可以看作是已经计算了的氵的层数
        Deque<Integer> stack = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty() || height[i] < height[stack.peek()]) {
                stack.push(i);
                continue;
            }

            // //当前元素等于栈顶元素，弹到不能弹为止
            // while(!stack.isEmpty() && height[i] == height[stack.peek()]) stack.pop();

            // 当前元素 >= 栈顶元素，开始弹栈并计算面积
            while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                int popIndex = stack.pop();
                if (!stack.isEmpty()) {
                    ans += (Math.min(height[i], height[stack.peek()]) - height[popIndex]) * (i - stack.peek() - 1);
                }
            }

            stack.push(i);
        }

        return ans;
    }
}
