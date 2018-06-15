package javac.concurrent.demo;

public class TransferRunnable implements Runnable {

    private Bank bank;
    private int fromAccount;
    private double maxAmount;
    private long DELAY = 10;

    public TransferRunnable(Bank b, int from, double max) {
        this.bank = b;
        this.fromAccount = from;
        this.maxAmount = max;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int toAccount = (int) (bank.size() * Math.random());
                double amount = maxAmount * Math.random();
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep((long) (DELAY * Math.random()));
            }
        } catch (Exception e) {

        }
    }

}
