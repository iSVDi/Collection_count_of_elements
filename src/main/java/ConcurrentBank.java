import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentBank {

    private final AtomicReference<List<BankAccount>> accountsUpdater = new AtomicReference<>(new ArrayList<>());

    public BankAccount createAccount(double amount) {
        BankAccount account = new BankAccount(new BigDecimal(amount));
        List<BankAccount> newAccounts = accountsUpdater.get();
        newAccounts.add(account);
        accountsUpdater.set(newAccounts);
        return account;
    }

    public void transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        BigDecimal transferAmount = new BigDecimal(amount);
        if (fromAccount.getBalance().subtract(transferAmount).compareTo(BigDecimal.ZERO) >= 0) {
            try {
                fromAccount.withdraw(transferAmount);
                toAccount.deposit(transferAmount);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public double getTotalBalance() {
        BigDecimal res = accountsUpdater.get().stream()
                .map(BankAccount::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return res.doubleValue();
    }

}
