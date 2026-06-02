import java.util.*;
import java.io.*;

// SOAL 3
// Custom Exception (Unchecked Exception)
// Digunakan ketika SKS mahasiswa tidak mencukupi
class SksTidakCukupException extends RuntimeException {

    public SksTidakCukupException(String pesan) {
        super(pesan);
    }
}

// SOAL 4
// Custom Exception (Checked Exception)
// Digunakan ketika kelas sudah penuh
class KelasPenuhException extends Exception {

    public KelasPenuhException(String pesan) {
        super(pesan);
    }
}


// SOAL 2 & SOAL 3
// Class Mahasiswa
// Menyimpan data dan operasi yang berkaitan
// dengan mahasiswa
class Mahasiswa {

    // Menyimpan sisa SKS mahasiswa
    int sisaSks = 5;


    // Method untuk mengatur SKS maksimal mahasiswa
    public void setSksMaksimal(int sks) {

        // Validasi batas SKS
        if (sks < 2 || sks > 24) {

            // Melempar exception secara manual
            throw new IllegalArgumentException(
                    "Kesalahan sistem: Batas SKS tidak valid (harus antara 2 - 24 SKS)!");
        }

        System.out.println("SKS maksimal berhasil disimpan: " + sks);
    }


    // Method untuk mengambil mata kuliah
    public void ambilMataKuliah(String namaMatkul, int bebanSks) {

        // Mengecek apakah SKS mencukupi
        if (bebanSks > sisaSks) {

            throw new SksTidakCukupException(
                    "SKS tidak cukup untuk mengambil " + namaMatkul);
        }

        // Mengurangi sisa SKS
        sisaSks -= bebanSks;

        System.out.println("Berhasil mengambil " + namaMatkul);
        System.out.println("Sisa SKS: " + sisaSks);
    }
}

// SOAL 4 & SOAL 5
// Class SistemAkademik
// Menangani proses akademik mahasiswa
class SistemAkademik {


    // Method untuk bergabung ke kelas
    // throws digunakan karena Checked Exception
    // wajib ditangani
    public void gabungKelas(String kodeKelas, int kuotaTersedia)
            throws KelasPenuhException {

        // Jika kuota habis
        if (kuotaTersedia <= 0) {

            throw new KelasPenuhException(
                    "Kelas " + kodeKelas + " sudah penuh.");
        }

        System.out.println("Berhasil masuk kelas " + kodeKelas);
    }

    // Method mencetak dokumen KRS
    public void cetakDokumenKrs(String namaFile)
            throws FileNotFoundException {

        // Jika file tidak sesuai
        if (!namaFile.equals("krs_valid.txt")) {

            throw new FileNotFoundException(
                    "File " + namaFile + " tidak ditemukan.");
        }

        System.out.println("Dokumen KRS berhasil dicetak.");
    }
}


// CLASS MAIN
// Tempat program dijalankan
public class Main {

    public static void main(String[] args) {

        // Scanner digunakan untuk input keyboard
        Scanner input = new Scanner(System.in);


        // SOAL 1
        // Persiapan Sistem dan Kuota Kelas
        System.out.println("SOAL 1");

        // Array untuk menyimpan 3 kuota mata kuliah
        int[] kuota = new int[3];

        try {

            // Sengaja dibuat 4 kali
            // agar memunculkan ArrayIndexOutOfBoundsException
            for (int i = 0; i < 4; i++) {

                System.out.print("Masukkan kuota ke-" + (i + 1) + ": ");

                kuota[i] = input.nextInt();
            }

        }
        // Menangkap input selain angka
        catch (InputMismatchException e) {

            System.out.println("Error: Input harus berupa angka!");
        }
        // Menangkap indeks array melebihi batas
        catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("Error: Array hanya berukuran 3!");
        }


        // SOAL 2
        // Validasi Data Mahasiswa
        System.out.println("\nSOAL 2");

        Mahasiswa mhs = new Mahasiswa();

        try {

            // Sengaja diisi 30 agar error
            mhs.setSksMaksimal(30);

        }
        catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());
        }


        // SOAL 3
        // Penambahan Mata Kuliah
        System.out.println("\nSOAL 3");

        try {

            // Beban SKS lebih besar dari sisa SKS
            mhs.ambilMataKuliah("Pemrograman Java", 8);

        }
        catch (SksTidakCukupException e) {

            System.out.println(e.getMessage());
        }


        // SOAL 4
        // Pengecekan Ketersediaan Kelas
        System.out.println("\nSOAL 4");

        SistemAkademik sistem = new SistemAkademik();

        try {

            // Kuota dibuat 0 agar exception muncul
            sistem.gabungKelas("IF101", 0);

        }
        catch (KelasPenuhException e) {

            System.out.println(e.getMessage());
        }

       
        // SOAL 5
        // Pencetakan Dokumen KRS
        System.out.println("\nSOAL 5");

        try {

            // Nama file sengaja salah
            sistem.cetakDokumenKrs("krs_salah.txt");

        }
        catch (FileNotFoundException e) {

            System.out.println(e.getMessage());
        }
        finally {

            // Akan selalu dijalankan
            System.out.println(
                    "Sesi Sistem Rencana Studi telah ditutup. Koneksi database diputuskan.");
        }

        // Menutup Scanner
        input.close();
    }
}