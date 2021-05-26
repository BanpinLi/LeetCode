public class Pro1 {
    public boolean checkZeroOnes(String s) {
        int countOne = 0, maxOneLen = 0;
        int countZero = 0, maxZeroLen = 0;
        for(int i = 0;i < s.length();i++) {
            if(s.charAt(i) == '1') {
                countOne++;
                maxZeroLen = Math.max(maxZeroLen, countZero);
                countZero = 0;
            } else {
                countZero++;
                maxOneLen = Math.max(maxOneLen, countOne);
                countOne = 0;
            }
        }
        maxZeroLen = Math.max(maxZeroLen, countZero);
        maxOneLen = Math.max(maxOneLen, countOne);
        return maxOneLen > maxZeroLen;
    }
}
