import java.util.*;

public class Pro2 {
    public int maximumRemovals(String s, String p, int[] removable) {
        //写一个函数来判断p是否是s的一个子字符串
        //通过二分的方法来不断确定ans，也就是需要返回的下标的位置
        int left = 0;
        int right = removable.length;
        int ans = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(isSubstring(s, p, removable, mid)) {
                ans = Math.max(ans, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
    
    //exp表示的是removable数组，k表示的是这个数组的有效长度是多少
    private boolean isSubstring(String s, String p, int[] exp, int k) {
        //将exp存入到一个map里面，后面直接进行map地查找，可以降低复杂度
        Map<Integer, Boolean> map = new HashMap<>();
        for(int i = 0;i < k;i++) {
            map.put(exp[i], true);
        }
        int index = 0;
        for(int i = 0;i < s.length();i++) {
            if(s.charAt(i) == p.charAt(index) && !map.containsKey(i)) {
                index++;
            }
            if(index == p.length()) {
                return true;
            }
        }
        return false;
    }
}
