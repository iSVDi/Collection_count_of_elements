import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class BankAccount {

    private final UUID id;
    private final AtomicReference<BigDecimal> updater;

    public BankAccount(BigDecimal amount) {
        this.id = UUID.randomUUID();
        this.updater = new AtomicReference<>(amount);
    }

    public void deposit(BigDecimal amount) {
        BigDecimal newAmount = updater.get().add(amount);
        updater.set(newAmount);
    }

    public void withdraw(BigDecimal amount) throws Exception {
        if (updater.get().compareTo(amount) >= 0) {
            BigDecimal newAmount = updater.get().subtract(amount);
            updater.set(newAmount);
        } else
            throw BankAccountExceptions.wrongWithdrawRequest();

    }

    public BigDecimal getBalance() {
        return updater.get();
    }
}


