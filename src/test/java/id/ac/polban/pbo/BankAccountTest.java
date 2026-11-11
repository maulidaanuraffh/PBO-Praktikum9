package id.ac.polban.pbo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void deposit_menambahSaldo() {
        // Arrange
        BankAccount acc = new BankAccount("001", "Andi", 100_000);

        // Act
        acc.deposit(50_000);

        // Assert
        assertEquals(150_000, acc.getBalance(), 0.001);
    }

    @Test
    void constructor_menyimpanDataDenganBenar() {
        // Arrange + Act
        BankAccount acc = new BankAccount("001", "Andi", 100_000);

        // Assert 
        assertAll("data rekening",
            () -> assertEquals("001", acc.getAccountNumber()),
            () -> assertEquals("Andi", acc.getOwner()),
            () -> assertEquals(100_000, acc.getBalance(), 0.001)
        );
    }

    @Test
    void withdraw_melebihiSaldo_ditolak() {
        // Arrange
        BankAccount acc = new BankAccount("001", "Andi", 100_000);

        // Act + Assert exception
        assertThrows(
            InsufficientBalanceException.class,
            () -> acc.withdraw(200_000)
        );

        // Assert state tetap tidak berubah setelah gagal
        assertEquals(100_000, acc.getBalance(), 0.001);
    }

    @Test
    void withdraw_valid_tidakMelempar() {
        // Arrange
        BankAccount acc = new BankAccount("001", "Andi", 100_000);

        // Act + Assert tidak throw
        assertDoesNotThrow(() -> acc.withdraw(30_000));

        // Assert saldo berkurang dengan benar
        assertEquals(70_000, acc.getBalance(), 0.001);
    }

    @Test
    void constructor_saldoNegatif_ditolak() {
        // Act + Assert exception sekaligus tangkap object-nya
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> new BankAccount("001", "Andi", -1)
        );

        // Assert pesan exception tepat
        assertEquals("Saldo awal tidak boleh negatif", ex.getMessage());
    }
}
