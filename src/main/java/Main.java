public class Main {

    public static void main(String[] args) {

        Bank bank = new Bank();

      new Thread(() -> {bank.addAccount("123456789", 100000);}).start();
      new Thread(() -> {bank.addAccount("987456321", 20000);}).start();
      new Thread(() -> {bank.addAccount("999988881", 350000);}).start();
      new Thread(() -> {bank.addAccount("987456322", 50000);}).start();
      new Thread(() -> {bank.addAccount("999988882", 55000);}).start();
      new Thread(() -> {bank.addAccount("987456323", 4000);}).start();
      new Thread(() -> {bank.addAccount("999988883", 65000);}).start();
      new Thread(() -> {bank.addAccount("987456324", 8000);}).start();
      new Thread(() -> {bank.addAccount("999988884", 135000000);}).start();
      new Thread(() -> {bank.addAccount("124456789", 10000);}).start();

              //  bank.getAccounts();

            new Thread(() -> {bank.getSumAllAccounts();}).start();

            new Thread(() -> {bank.getBalance("987456321");}).start();
            new Thread(() -> {bank.getBalance("123456789");}).start();
            new Thread(() -> {bank.getBalance("999988884");}).start();
            new Thread(() -> {bank.getBalance("999988882");}).start();

            new Thread(() -> {
                    try {
                            bank.transfer("987456321","999988882", 1300);
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
            }).start();
            new Thread(() -> {bank.getBalance("987456321");}).start();
            new Thread(() -> {bank.getBalance("999988882");}).start();

            new Thread(() -> {
                    try {
                            bank.transfer("999988884","987456324", 155000 );
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
            }).start();
            new Thread(() -> {bank.getBalance("999988884");}).start();
            new Thread(() -> {bank.getBalance("987456324");}).start();

            new Thread(() -> {
                    try {
                            bank.transfer("999988881","124456789", 60000 );
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
            }).start();
            new Thread(() -> {bank.getBalance("999988881");}).start();
            new Thread(() -> {bank.getBalance("124456789");}).start();

        new Thread(() -> {
            try {
                bank.transfer("124456789","999988881", 57200 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {bank.getBalance("999988881");}).start();
        new Thread(() -> {bank.getBalance("124456789");}).start();


        new Thread(() -> {bank.getSumAllAccounts();}).start();

    }

}
