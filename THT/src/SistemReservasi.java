import java.util.ArrayList;

public class SistemReservasi {

    private ArrayList<KeretaApi> daftarKereta;

    public SistemReservasi() {

        daftarKereta = new ArrayList<>();

        // Data awal sesuai soal
        daftarKereta.add(
                new KeretaApi(
                        "K01",
                        "Argo Bromo",
                        "JKT - SBY",
                        50));

        daftarKereta.add(
                new KeretaApi(
                        "K02",
                        "Parahyangan",
                        "JKT - BDG",
                        15));
    }

    // Menampilkan semua jadwal
    public void tampilkanJadwal() {

        System.out.println("\nDAFTAR KERETA");

        for (KeretaApi kereta : daftarKereta) {

            System.out.println("Kode       : " + kereta.getKodeKereta());
            System.out.println("Nama       : " + kereta.getNamaKereta());
            System.out.println("Rute       : " + kereta.getRutePerjalanan());
            System.out.println("Sisa Kursi : " + kereta.getSisaKursi());
            System.out.println();
        }
    }

    // Method pemesanan tiket
    public void pesanTiket(
            String kodeKereta,
            String nikPenumpang,
            String namaPenumpang,
            int jumlahTiket)

            throws RuteTidakDitemukanException,
            TiketHabisException {

        // Validasi NIK
        if (!nikPenumpang.matches("\\d{16}")) {

            throw new DataPenumpangTidakValidException(
                    "NIK harus terdiri dari 16 digit angka");
        }

        KeretaApi keretaDitemukan = null;

        // Cari kereta berdasarkan kode
        for (KeretaApi kereta : daftarKereta) {

            if (kereta.getKodeKereta().equalsIgnoreCase(kodeKereta)) {

                keretaDitemukan = kereta;
                break;
            }
        }

        // Jika tidak ditemukan
        if (keretaDitemukan == null) {

            throw new RuteTidakDitemukanException(
                    "Kode kereta tidak ditemukan");
        }

        // Cek sisa kursi
        if (jumlahTiket > keretaDitemukan.getSisaKursi()) {

            throw new TiketHabisException(
                    keretaDitemukan.getNamaKereta(),
                    keretaDitemukan.getSisaKursi());
        }

        // Kurangi kursi jika berhasil
        keretaDitemukan.setSisaKursi(
                keretaDitemukan.getSisaKursi() - jumlahTiket);

        System.out.println("\nPEMESANAN BERHASIL");
        System.out.println("Nama Penumpang : " + namaPenumpang);
        System.out.println("NIK            : " + nikPenumpang);
        System.out.println("Kereta         : " + keretaDitemukan.getNamaKereta());
        System.out.println("Jumlah Tiket   : " + jumlahTiket);
    }
}