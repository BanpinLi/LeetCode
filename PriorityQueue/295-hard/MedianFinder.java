package src;

import java.util.*;

// class MedianFinder {
//     List<Integer> nums;
//     int size = 0;
//     boolean isSorted;

//     /** initialize your data structure here. */
//     public MedianFinder() {
//         nums = new ArrayList<>();
//         isSorted = false;
//     }

//     public void addNum(int num) {
//         nums.add(num);
//         size++;
//         isSorted = false;
//     }

//     public double findMedian() {
//         if(!isSorted) {
//             nums.sort((a, b) -> a - b);
//             isSorted = true;
//         }
//         if(size % 2 == 1) {
//             return nums.get(size / 2);
//         } else {
//             return (nums.get(size / 2 - 1) + nums.get(size / 2)) / 2.0;
//         }
//     }
// }

class MedianFinder {
    // 准备两个堆，一个大根，一个小根，堆顶的元素就是中位数
    PriorityQueue<Integer> leftHeap;
    PriorityQueue<Integer> rightHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        leftHeap = new PriorityQueue<>((a, b) -> b - a);
        rightHeap = new PriorityQueue<>((a, b) -> a - b);
    }

    public void addNum(int num) {
        // 保证两个堆size相差不超过2，左堆顶小于右堆顶
        if (leftHeap.size() <= rightHeap.size()) {
            leftHeap.offer(num);
        } else {
            rightHeap.offer(num);
        }

        if (!leftHeap.isEmpty() && !rightHeap.isEmpty() && leftHeap.peek() > rightHeap.peek()) {
            int temp = leftHeap.poll();
            leftHeap.offer(rightHeap.poll());
            rightHeap.offer(temp);
        }
    }

    public double findMedian() {
        if (leftHeap.size() != rightHeap.size()) {
            return leftHeap.size() > rightHeap.size() ? leftHeap.peek() : rightHeap.peek();
        } else {
            return (leftHeap.peek() + rightHeap.peek()) / 2.0;
        }
    }
}