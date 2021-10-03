package src;

public class Pro3 {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(maxConsecutiveAnswers(answerKey, k, 'T'), maxConsecutiveAnswers(answerKey, k, 'F'));
    }

    private int maxConsecutiveAnswers(String answerKey, int k, char ch) {
        // 滑动窗口
        int left, right, ret;
        ret = left = right = 0;
        while (right < answerKey.length()) {
            // 向右扫描直到用完k值
            while (right < answerKey.length() && (k > 0 || answerKey.charAt(right) == ch)) {
                if (answerKey.charAt(right) != ch) {
                    k--;
                }
                right++;
            }
            right--;
            // 此时right停留在最长的位置，left也是最长的位置
            ret = Math.max(ret, right - left + 1);

            while (left < answerKey.length() && k == 0) {
                if (answerKey.charAt(left) != ch) {
                    k++;
                }
                left++;
            }

            right++;
        }

        return ret;
    }
}
