import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner masukanPengguna = new Scanner(System.in);

        SistemReservasi sistemReservasi = new SistemReservasi();

        int pilihanMenu = 0;

        do {

            try {

                System.out.println("JAVA EXPRESS");
                System.out.println("1. Lihat Jadwal");
                System.out.println("2. Pesan Tiket");
                System.out.println("3. Keluar");
                System.out.print("Pilih menu : ");

                pilihanMenu = masukanPengguna.nextInt();

                // Membersihkan enter
                masukanPengguna.nextLine();

                switch (pilihanMenu) {

                    case 1:

                        sistemReservasi.tampilkanJadwal();
                        break;

                    case 2:

                        System.out.print("Kode Kereta : ");
                        String kodeKereta =
                                masukanPengguna.nextLine();

                        System.out.print("NIK : ");
                        String nikPenumpang =
                                masukanPengguna.nextLine();

                        System.out.print("Nama Penumpang : ");
                        String namaPenumpang =
                                masukanPengguna.nextLine();

                        System.out.print("Jumlah Tiket : ");
                        int jumlahTiket =
                                masukanPengguna.nextInt();

                        masukanPengguna.nextLine();

                        sistemReservasi.pesanTiket(
                                kodeKereta,
                                nikPenumpang,
                                namaPenumpang,
                                jumlahTiket);

                        break;

                    case 3:

                        System.out.println(
                                "Terima kasih telah menggunakan JAVA EXPRESS");
                        break;

                    default:

                        System.out.println(
                                "Menu tidak tersedia");
                }

            }

            // Error saat user memasukkan huruf
            catch (InputMismatchException errorInput) {

                System.out.println(
                        "Input harus berupa angka!");

                // Membersihkan buffer
                masukanPengguna.nextLine();
            }

            // Custom Exception Unchecked
            catch (DataPenumpangTidakValidException errorData) {

                System.out.println(
                        "Error Data Penumpang : "
                                + errorData.getMessage());
            }

            // Custom Exception Checked
            catch (RuteTidakDitemukanException errorRute) {

                System.out.println(
                        "Error : "
                                + errorRute.getMessage());
            }

            // Custom Exception Checked
            catch (TiketHabisException errorTiket) {

                System.out.println(
                        "Tiket pada kereta "
                                + errorTiket.getNamaKereta()
                                + " tidak mencukupi.");

                System.out.println(
                        "Sisa kursi tersedia : "
                                + errorTiket.getSisaKursi());
            }

            // Menangkap error lain
            catch (Exception errorLain) {

                System.out.println(
                        "Terjadi kesalahan : "
                                + errorLain.getMessage());
            }

            // Pasti dijalankan
            finally {

                System.out.println(
                        "\nProses selesai.");
            }

        } while (pilihanMenu != 3);

        masukanPengguna.close();
    }
}