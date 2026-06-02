import java.util.*;

class SaldoTidakMencukupiException extends Exception {

    public SaldoTidakMencukupiException(String pesan) {
        super(pesan);
    }
}

class BatasTransferHarianException extends Exception {

    public BatasTransferHarianException(String pesan) {
        super(pesan);
    }
}

// Class untuk menyimpan data rekening bank
class AkunBank {

    // Atribut rekening
    private String nomorRekening;
    private double saldo;
    private double totalTransferHariIni;

    // Batas transfer per hari
    private final double LIMIT_TRANSFER = 10000000;

    // Constructor
    public AkunBank(String nomorRekening, double saldo) {
        this.nomorRekening = nomorRekening;
        this.saldo = saldo;
        this.totalTransferHariIni = 0;
    }

    // Method untuk tarik tunai
    public void tarikTunai(double nominal)
            throws SaldoTidakMencukupiException {

        if (nominal > saldo) {
            throw new SaldoTidakMencukupiException(
                    "Saldo tidak mencukupi untuk penarikan.");
        }

        saldo -= nominal;

        System.out.println("Tarik tunai berhasil Rp" + nominal);
        System.out.println("Sisa saldo: Rp" + saldo);
    }

    // Method untuk transfer uang
    public void transfer(AkunBank tujuan, double nominal)
            throws SaldoTidakMencukupiException,
            BatasTransferHarianException {

        if (nominal > saldo) {
            throw new SaldoTidakMencukupiException(
                    "Saldo tidak mencukupi untuk transfer.");
        }

        if (totalTransferHariIni + nominal > LIMIT_TRANSFER) {
            throw new BatasTransferHarianException(
                    "Transfer melebihi batas harian Rp10.000.000");
        }

        saldo -= nominal;
        tujuan.saldo += nominal;
        totalTransferHariIni += nominal;

        System.out.println("Transfer berhasil Rp" + nominal);
        System.out.println("Total transfer hari ini: Rp"
                + totalTransferHariIni);
    }

    // Method untuk melihat saldo
    public double getSaldo() {
        return saldo;
    }
}

public class Main {

    public static void main(String[] args) {

        AkunBank akunBilly =
                new AkunBank("123456789", 5000000);

        AkunBank akunBudi =
                new AkunBank("987654321", 3000000);

        try {

            System.out.println("Percobaan tarik tunai");

            akunBilly.tarikTunai(2000000);

            akunBilly.tarikTunai(4000000);

            System.out.println();

            System.out.println("Percobaan transfer");

            akunBilly.transfer(
                    akunBudi,
                    11000000);

        }

        // Menangani error saldo tidak cukup
        catch (SaldoTidakMencukupiException e) {

            System.out.println(
                    "Error saldo: " + e.getMessage());
        }

        // Menangani error batas transfer
        catch (BatasTransferHarianException e) {

            System.out.println(
                    "Error transfer: " + e.getMessage());
        }

        // Selalu dijalankan di akhir program
        finally {

            System.out.println();
            System.out.println(
                    "Sesi transaksi ATM Anda telah diakhiri. Kartu dikeluarkan otomatis.");
        }
    }
}