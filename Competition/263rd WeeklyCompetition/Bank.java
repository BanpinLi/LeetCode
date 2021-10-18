package src;

class Bank {

    private long[] bank;
    private int number;

    public Bank(long[] balance) {
        bank = new long[balance.length];
        for (int i = 0; i < balance.length; i++) {
            bank[i] = balance[i];
        }
        number = balance.length;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 > number || account2 > number) {
            return false;
        }
        if (bank[account1 - 1] < money) {
            return false;
        }
        bank[account1 - 1] -= money;
        bank[account2 - 1] += money;
        return true;
    }

    public boolean deposit(int account, long money) {
        if (account > number) {
            return false;
        }
        bank[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (account > number) {
            return false;
        }
        if (bank[account - 1] < money) {
            return false;
        }
        bank[account - 1] -= money;
        return true;
    }
}
