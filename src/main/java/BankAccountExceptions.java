public class BankAccountExceptions {
    static Exception wrongWithdrawRequest() {
        return new Exception("Don't enough amount on account");
    }
}
