import java.util.*;

class Solution {
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        String[] low = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
                "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
        String[] mid = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
        String[] unit = { "", "Thousand", "Million", "Billion" };

        List<String> arr = new ArrayList<>();

        // 三个一组
        int group = 0;
        while (num > 0) {
            int pOne = num % 10;
            num /= 10;
            int pTwo = num % 10;
            num /= 10;
            int pThree = num % 10;
            num /= 10;
            if (pOne != 0 || pTwo != 0 || pThree != 0) {
                arr.add(unit[group]);
            }

            // arr.add(unit[group]);
            if (pTwo == 1) {
                arr.add(low[pOne + 10]);
            } else {
                arr.add(low[pOne]);
                if (pTwo != 0) {
                    arr.add(mid[pTwo]);
                }
            }

            if (pThree != 0) {
                StringBuilder temp = new StringBuilder();
                temp.append(low[pThree]).append(" Hundred");
                arr.add(temp.toString());
            }
            group++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = arr.size() - 1; i > 0; i--) {
            if (arr.get(i).length() == 0) {
                continue;
            }
            sb.append(arr.get(i)).append(" ");
        }
        if (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}