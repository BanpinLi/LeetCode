import java.util.*;

public class Solution {
    public boolean judgeSquareSum(int c) {
        //首先判断是不是平方数，是就返回
        //用一个set集合来存储小于c的所有的平方数，然后遍历set来查找是否可以组成c
        int num = (int)Math.sqrt(c);
        if(num * num == c) return true;

        Set<Integer> set = new HashSet<>();
        for(int i = 1; i <= num; i++) {
            set.add(i * i);
        }

        for(int number : set) {
            if(set.contains(c - number)) {
                return true;
            }
        }
        return false;
    }
}
