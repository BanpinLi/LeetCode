package src;

import java.util.*;

public class Pro1 {
    public boolean areNumbersAscending(String s) {
        String[] nums = s.split(" ");
        List<Integer> arr = new ArrayList<>();
        for (String num : nums) {
            try {
                int number = Integer.parseInt(num);
                arr.add(number);
            } catch (Exception e) {

            }
        }
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) <= arr.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
