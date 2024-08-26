import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentBank {

    private final AtomicReference<List<BankAccount>> accountsUpdater = new AtomicReference<>(new ArrayList<>());
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public  BankAccount createAccount(double amount) {
        lock.writeLock().lock();
        BankAccount account = new BankAccount(new BigDecimal(amount));
        List<BankAccount> newAccounts = accountsUpdater.get();
        newAccounts.add(account);
        accountsUpdater.set(newAccounts);
        lock.writeLock().unlock();
        return account;
    }

    public void transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        BigDecimal transferAmount = new BigDecimal(amount);
        if (fromAccount.getBalance().subtract(transferAmount).compareTo(BigDecimal.ZERO) >= 0) {
            try {
                lock.writeLock().lock();
                fromAccount.withdraw(transferAmount);
                toAccount.deposit(transferAmount);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {
                lock.writeLock().unlock();
            }
        }
    }

    public double getTotalBalance() {
        lock.writeLock().lock();
        BigDecimal res = accountsUpdater.get().stream()
                .map(BankAccount::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        lock.writeLock().unlock();
        return res.doubleValue();

    }

    Integer getNumAccounts() {
        return accountsUpdater.get().size();
    }

}
