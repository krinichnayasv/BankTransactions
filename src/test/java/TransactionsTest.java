import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TransactionsTest extends TestCase {

    Bank bank = new Bank();

    @Before
    @Override
    protected void setUp() throws Exception {

        bank.addAccount("123456789", 100000);
        bank.addAccount("987456321", 20000);
        bank.addAccount("999988881", 350000);
        bank.addAccount("987456322", 50000);
        bank.addAccount("999988882", 55000);
        bank.addAccount("987456323", 4000);
        bank.addAccount("999988883", 65000);
        bank.addAccount("987456324", 8000);
        bank.addAccount("999988884", 135000000);
        bank.addAccount("124456789", 10000);

    }


    @Test
    public void testGetSumAllAccounts (){
        // переменная, которая получится в результате теста
        long actual = bank.getSumAllAccounts();
        // ожидаемый результат
        long expected = 135662000;
        // метод, сравнивающий ожидаемый и полученный результаты и выдающий нужное исключение
        assertEquals(expected,actual);

    }

    @Test
    public void testGetBalance () {
        long actual1 = bank.getBalance("987456321");
        long expected1 = 20000;
        long actual2 = bank.getBalance("123456789");
        long expected2 = 100000;
        long actual3 = bank.getBalance("999988884");
        long expected3 = 135000000;
        long actual4 = bank.getBalance("999988882");
        long expected4 = 55000;
        assertEquals(expected1,actual1);
        assertEquals(expected2,actual2);
        assertEquals(expected3,actual3);
        assertEquals(expected4,actual4);
    }




}
