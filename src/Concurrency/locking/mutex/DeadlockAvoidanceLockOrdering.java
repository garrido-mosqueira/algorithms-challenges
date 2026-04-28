package Concurrency.locking.mutex;

/**
 * Demonstrates deadlock prevention by acquiring locks in a global order.
 */
public class DeadlockAvoidanceLockOrdering {

    private static final Object tieLock = new Object();

    static class Account {
        private final int id;
        private int balance;
        private final Object lock = new Object();

        Account(int id, int balance) {
            this.id = id;
            this.balance = balance;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account accountA = new Account(1, 1_000);
        Account accountB = new Account(2, 1_000);

        Thread t1 = new Thread(() -> transfer(accountA, accountB, 100), "transfer-a-to-b");
        Thread t2 = new Thread(() -> transfer(accountB, accountA, 150), "transfer-b-to-a");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final balances -> A=" + accountA.balance + ", B=" + accountB.balance);
    }

    private static void transfer(Account from, Account to, int amount) {
        Object firstLock;
        Object secondLock;

        if (from.id < to.id) {
            firstLock = from.lock;
            secondLock = to.lock;
        } else if (from.id > to.id) {
            firstLock = to.lock;
            secondLock = from.lock;
        } else {
            // Rare case: identical IDs, use a tie-break lock for deterministic ordering.
            synchronized (tieLock) {
                synchronized (from.lock) {
                    synchronized (to.lock) {
                        doTransfer(from, to, amount);
                    }
                }
            }
            return;
        }

        synchronized (firstLock) {
            synchronized (secondLock) {
                doTransfer(from, to, amount);
            }
        }
    }

    private static void doTransfer(Account from, Account to, int amount) {
        if (from.balance < amount) {
            System.out.println(Thread.currentThread().getName() + " skipped transfer; insufficient funds");
            return;
        }

        from.balance -= amount;
        to.balance += amount;
        System.out.println(Thread.currentThread().getName() + " moved " + amount);
    }
}
