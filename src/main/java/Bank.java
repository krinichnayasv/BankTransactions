
import java.util.*;

public class Bank {


    private  Map<String, Account> accounts;
    private final Random random = new Random();
    private boolean access;

    public Bank () {
        accounts = new HashMap<>();
    }


    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {

        if(!accounts.containsKey(fromAccountNum) && !accounts.containsKey("!".concat(fromAccountNum))) {
            System.out.println("Счет " + fromAccountNum + " не найден!");
        }
        if(!accounts.containsKey(toAccountNum) && !accounts.containsKey("!".concat(toAccountNum))) {
            System.out.println("Счет " + toAccountNum + "не найден!");
        }


            if (!getAccess(fromAccountNum) || !getAccess(toAccountNum)) {
                System.out.println("The account is blocked! Operation is impossible.");
            } else if (amount < 50000 && getBalance(fromAccountNum) < amount) {
                System.out.println("На счете " + fromAccountNum + "недостаточно средств для перевода");
            } else  if  (amount > 50000 && getBalance(fromAccountNum) > amount) synchronized (this) {
                if (isFraud(fromAccountNum, toAccountNum, amount) == true) {
                    System.out.println("isFraud: " + isFraud(fromAccountNum, toAccountNum, amount));
                    setBlock(fromAccountNum);
                    setBlock(toAccountNum);
                }
            } else synchronized (this) {
                accounts.get(fromAccountNum).setMoney(getBalance(fromAccountNum) - amount);
                accounts.get(toAccountNum).setMoney(getBalance(toAccountNum) + amount);
            }

    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {

        long balance = 0;

        if (accounts.containsKey(accountNum)) {
            balance = accounts.get(accountNum).getMoney();
        } else if (accounts.containsKey("!".concat(accountNum))) {
            balance = accounts.get("!".concat(accountNum)).getMoney();
        } else {
            System.out.println("Счет " + accountNum + " не найден!");
        }

        System.out.println("Баланс счета " + accountNum + ": " + balance);

        return balance;
    }

    public long getSumAllAccounts() {

        long allBalance = 0;

        allBalance = accounts.values().stream().mapToLong(acc -> acc.getMoney()).sum();

        System.out.println("Баланс банка: " + allBalance);
        return allBalance;
    }

    public  boolean getAccess (String accNumber) {
    if (accounts.containsKey("!".concat(accNumber))) {
        access = false;
        System.out.println("Счет " + accNumber + " заблокирован!");
    }  else { access = true;}

        return access;
    }

    private synchronized void  setBlock (String accNumber) {
        accounts.put("!".concat(accNumber), accounts.get(accNumber));
        accounts.remove(accNumber, accounts.get(accNumber));
        System.out.println("Счет " + accNumber + " заблокирован СБ банка");
        }

        // если надо будет разблокировать счет

    private synchronized void  unBlock (String accNumber) {
        accounts.put(accNumber, accounts.get("!".concat(accNumber)));
        accounts.remove("!".concat(accNumber), accounts.get("!".concat(accNumber)));
       }

    public Map<String, Account> getAccounts() {
        for(String acc : accounts.keySet()) {
            System.out.println( acc + " - " +  accounts.get(acc).getMoney());
        }
            return accounts;
    }


    public  void addAccount(String accNumber, long money) {

        accounts.put(accNumber, new Account(accNumber, money));
    }

}
