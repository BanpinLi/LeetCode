import java.util.*;

class Solution {
    public int maximumGap(int[] nums) {
        Sort.radixSort(nums);
        int maxMargin = 0;
        for (int i = 1; i < nums.length; i++) {
            maxMargin = Math.max(maxMargin, nums[i] - nums[i - 1]);
        }
        return maxMargin;
    }
}

class Sort {

    // 冒泡排序
    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    // 插入排序
    public static void insertSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[j];
                    for (int k = j; k > i; k--) {
                        nums[k] = nums[k - 1];
                    }
                    nums[i] = temp;
                }
            }
        }
    }

    // 选择排序
    public static void selectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int curMin = nums[i];
            int sub = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < curMin) {
                    curMin = nums[j];
                    sub = j;
                }
            }
            swap(nums, i, sub);
        }
    }

    // 归并排序
    public static void mergeSort(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return;
        }

        int len1 = len / 2;
        int len2 = len - len1;
        int[] nums1 = new int[len1];
        int[] nums2 = new int[len2];
        for (int i = 0; i < len; i++) {
            if (i < len1) {
                nums1[i] = nums[i];
            } else {
                nums2[i - len1] = nums[i];
            }
        }

        mergeSort(nums1);
        mergeSort(nums2);

        int p = 0;
        int p1 = 0;
        int p2 = 0;
        while (p1 < len1 || p2 < len2) {
            int num1 = p1 < len1 ? nums1[p1] : Integer.MAX_VALUE;
            int num2 = p2 < len2 ? nums2[p2] : Integer.MAX_VALUE;
            if (num1 < num2) {
                nums[p++] = nums1[p1++];
            } else {
                nums[p++] = nums2[p2++];
            }
        }
    }

    // 快速排序
    public static void quickSort(int[] nums) {
        // 基于原地的算法，不需要额外的空间(不算堆栈)，需要另一个函数来进行递归
        quickSort(nums, 0, nums.length);
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start == end || start + 1 == end) {
            return;
        }

        Random r = new Random();
        int randomSub = r.nextInt(end - start) + start;
        int flagNum = nums[randomSub];
        swap(nums, start, randomSub);

        int left = start + 1;
        int right = end - 1;
        while (left < right) {
            while (left < right && nums[left] <= flagNum) {
                left++;
            }
            while (left < right && nums[right] > flagNum) {
                right--;
            }
            if (left != right) {
                swap(nums, left, right);
            }
        }
        int flagSub = left;
        if (nums[left] > flagNum) {
            flagSub = left - 1;
            swap(nums, left - 1, start);
        } else {
            swap(nums, left, start);
        }

        quickSort(nums, start, flagSub);
        quickSort(nums, flagSub + 1, end);
    }

    // 堆排序
    public static void heapSort(int[] nums) {
        int[] heap = new int[nums.length];
        int index = 0;// 插入元素的位置
        for (int i = 0; i < nums.length; i++) {
            insertElement(heap, index++, nums[i]);
        }
        int size = nums.length;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = pollElemet(heap, size--);
        }
    }

    // 向堆中插入元素
    private static void insertElement(int[] nums, int index, int element) {
        nums[index] = element;
        while (index != 0) {
            int fatherNode = (index - 1) / 2;
            if (nums[index] < nums[fatherNode]) {
                swap(nums, index, fatherNode);
                index = fatherNode;
            } else {
                break;
            }
        }
    }

    // 从堆中弹出顶端元素
    private static int pollElemet(int[] nums, int size) {
        // size是堆中存在多少元素
        int ret = nums[0];
        swap(nums, 0, size - 1);
        int index = 0;
        while (true) {
            int leftChild = index * 2 + 1;
            int rightChild = leftChild + 1;
            if (leftChild > size - 2) {
                break;
            } else if (leftChild == size - 2) {
                if (nums[index] > nums[leftChild]) {
                    swap(nums, index, leftChild);
                }
                break;
            } else if (nums[index] > nums[leftChild] || nums[index] > nums[rightChild]) {
                if (nums[leftChild] < nums[rightChild]) {
                    swap(nums, index, leftChild);
                    index = leftChild;
                } else {
                    swap(nums, index, rightChild);
                    index = rightChild;
                }
            } else {
                break;
            }
        }
        return ret;
    }

    // 桶排序
    public static void bucketSort(int[] nums) {

    }

    // 计数排序
    public static void countSort(int[] nums) {
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            minValue = Math.min(minValue, nums[i]);
            maxValue = Math.max(maxValue, nums[i]);
        }

        int[] countNums = new int[maxValue - minValue + 1];
        for (int i = 0; i < nums.length; i++) {
            countNums[nums[i] - minValue]++;
        }

        int index = 0;
        for (int i = 0; i < countNums.length; i++) {
            for (int j = 0; j < countNums[i]; j++) {
                nums[index++] = i + minValue;
            }
        }
    }

    // 基数排序
    public static void radixSort(int[] nums) {
        // 获得最大值，并以此确定分组次数
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            maxValue = Math.max(maxValue, nums[i]);
        }
        int groupCount = 0;
        while (maxValue > 0) {
            maxValue /= 10;
            groupCount++;
        }

        int times = 1;
        for (int i = 0; i < groupCount; i++) {
            ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                bucketList.add(new ArrayList<>());
            }
            for (int num : nums) {
                int groupIndex = num / times % 10;
                ArrayList<Integer> temp = bucketList.get(groupIndex);
                temp.add(num);
                bucketList.set(groupIndex, temp);
            }
            int index = 0;
            for (ArrayList<Integer> tempList : bucketList) {
                for (int num : tempList) {
                    nums[index++] = num;
                }
            }
            times *= 10;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}