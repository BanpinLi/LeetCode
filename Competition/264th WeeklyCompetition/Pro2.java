package src;

public class Pro2 {
    public int nextBeautifulNumber(int n) {
        n++;
        while (!isBalance(n)) {
            n++;
        }
        return n;
    }

    private boolean isBalance(int num) {
        int len = 0;
        int temp = num;
        while (temp > 0) {
            len++;
            temp /= 10;
        }

        int[] bits = new int[len];
        for (int i = 0; i < len; i++) {
            bits[i] = num % 10;
            num /= 10;
        }
        int[] table = new int[10];
        for (int bit : bits) {
            table[bit]++;
            if (table[bit] > bit) {
                return false;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (table[i] != 0 && table[i] != i) {
                return false;
            }
        }
        return true;
    }
}
