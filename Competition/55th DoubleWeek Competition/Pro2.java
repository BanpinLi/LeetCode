public class Pro2 {
    public String removeOccurrences(String s, String part) {
        //使用递归和滑动窗口
        if(s.length() < part.length()) {
            return s;
        }

        int len = part.length();
        int index = 0;
        boolean isFind = false;
        for(int i = 0;i <= s.length() - len;i++) {
            if(s.substring(i, i + len).equals(part)) {
                index = i;
                isFind = true;
                break;
            }
        }
        if(isFind) {
            return removeOccurrences(s.substring(0, index) + s.substring(index + len), part);
        } else {
            return s;
        }
    }
}
