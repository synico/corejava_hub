package javac.concurrent.deadlock;

public class MoneyTranser {
    
    private static final Object tieLock = new Object();
    
    public void transferMoney(final Account fromAcct, final Account toAcct, final int amount) {
        class Helper {
            public void transfer() {
                fromAcct.debit(amount);
                toAcct.credit(amount);
            }
        }
        
        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);
        
        if(fromHash < toHash) {
            synchronized(fromAcct) {
                synchronized(toAcct) {
                    new Helper().transfer();
                }
            }
        } else if(fromHash > toHash) {
            synchronized(toAcct) {
                synchronized(fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized(tieLock) {
                synchronized(fromAcct) {
                    synchronized(toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }
    
}
