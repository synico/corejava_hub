package javac.concurrent.deadlock;

import java.util.Random;

public class DemonstrateDeadLock {
    
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 10000000;

    public static void main(String[] args) {
        final Random rnd = new Random();
        final Account [] accounts = new Account[NUM_ACCOUNTS];
        
        for(int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
        }
        
        MoneyTranser mt = new MoneyTranser();
        
        class TransferThread extends Thread {
            public void run() {
                for(int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int amount = rnd.nextInt(1000);
                    mt.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
            }
        }
        
        for(int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }
}
