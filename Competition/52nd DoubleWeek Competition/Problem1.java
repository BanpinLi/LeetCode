class Problem1 {
    public String sortSentence(String s) {
        String[] sArr = s.split(" ");
        String[] ss = new String[11];
        for(int i = 0;i < sArr.length;i++) {
            int sub = sArr[i].charAt(sArr[i].length() - 1) - '0';
            ss[sub] = sArr[i].substring(0, sArr[i].length() - 1);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i < 10;i++) {
            sb.append(ss[i]);
            if(ss[i + 1] == null) {
                break;
            } else {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
}