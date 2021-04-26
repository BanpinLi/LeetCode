class Solution {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(shipWithDays(nums, 5));
    }

    public static int shipWithDays(int[] weights, int D) {
        //基本思路：枚举每一个可能的ans，选择那些符合条件并且最小的
        //如何进行高效的枚举：确定上下限，并且采用二分的方?
        int ans = Integer.MAX_VALUE;

        //遍历数组得到下限和上?
        int sum, lowValue, upValue = 0;
        lowValue = 0;
        sum = 0;
        for(int num : weights) {
            lowValue = Math.max(num, lowValue);
            sum += num;
        }
        upValue = sum;

        int midValue;
        //使用二分查找来进行遍?
        while(lowValue < upValue) {
            midValue = (lowValue + upValue) / 2;
            //判断是否是符合条件的，符合就更新ans
            int count = 0; //记录分组组数
            int number; //记录组内的和
            for(int i = 0;i < weights.length;i++) {
                number = weights[i];
                while(number <= midValue) {
                    i++;
                    if(i == weights.length) {
                        break;
                    }
                    number += weights[i];
                }
                i--; //退出循环一定是某一个条件不满足，所以i--就是将i放到最后满足条件的位置

                count++;
                if(count > D) {
                    break;
                }
            }

            //符合条件，缩小边界，更新ans,否则缩小左边?
            if(count <= D) {
                upValue = midValue - 1;
                ans = Math.min(midValue, ans);
            } else {
                lowValue = midValue + 1;
            }
            //System.out.println(ans);
        }
        return ans;
    }
}