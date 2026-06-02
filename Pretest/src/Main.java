import java.util.*;

class KalkulatorPembagian {

    public double bagi(int pembilang, int penyebut) {
        if (penyebut == 0) {
            throw new ArithmeticException("Tidak dapat membagi dengan nol.");
        }
        return (double) pembilang / penyebut;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        KalkulatorPembagian kalkulator = new KalkulatorPembagian();

        try {
            System.out.print("Masukkan pembilang: ");
            int pembilang = input.nextInt();

            System.out.print("Masukkan penyebut: ");
            int penyebut = input.nextInt();

            double hasil = kalkulator.bagi(pembilang, penyebut);

            System.out.println("Hasil pembagian = " + hasil);

        } catch (ArithmeticException e) {
            System.out.println("Error: Penyebut tidak boleh bernilai 0!");

        } catch (InputMismatchException e) {
            System.out.println("Error: Input harus berupa angka!");

        } finally {
            input.close();
            System.out.println("Proses kalkulasi selesai dan resource memori telah dibersihkan.");
        }
    }
}