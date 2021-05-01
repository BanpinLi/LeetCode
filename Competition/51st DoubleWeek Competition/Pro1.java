public class Pro1 {
    public String replaceDigits(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for(int i = 1;i < len;i = i + 2) {
            sb.append(s.charAt(i - 1)).append((char)(s.charAt(i - 1) + s.charAt(i) - '0'));
        }
        if(len % 2 != 0) {
            sb.append(s.charAt(len - 1));
        }
        return sb.toString();
    }
}
