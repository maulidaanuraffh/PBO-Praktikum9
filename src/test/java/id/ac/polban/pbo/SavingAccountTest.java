package id.ac.polban.pbo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {

    @Test
    void aturanSubclass_berlakuLewatReferenceInduk() {
        // Arrange tipe variabel BankAccount, object SavingsAccount
        BankAccount acc = new SavingsAccount("003", "Citra", 100_000, 0.05);

        // Act + Assert exception
        assertThrows(
            InsufficientBalanceException.class,
            () -> acc.withdraw(60_000)
        );

        // Assert state tetap tidak berubah
        assertEquals(100_000, acc.getBalance(), 0.001);
    }

    @Test
    void withdraw_tepatBatasMinimum_diterima()
            throws InsufficientBalanceException {
        // Arrange
        SavingsAccount savings = new SavingsAccount(
            "003", "Carmen", 100_000, 0.05);

        // Act
        savings.withdraw(50_000);

        // Assert (saldo tepat di batas minimum)
        assertEquals(SavingsAccount.MIN_BALANCE, savings.getBalance(), 0.001);
    }
}
