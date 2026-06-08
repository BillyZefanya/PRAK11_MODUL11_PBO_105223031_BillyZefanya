public class KeretaApi {

    private String kodeKereta;
    private String namaKereta;
    private String rutePerjalanan;
    private int sisaKursi;

    public KeretaApi(String kodeKereta,
                     String namaKereta,
                     String rutePerjalanan,
                     int sisaKursi) {

        this.kodeKereta = kodeKereta;
        this.namaKereta = namaKereta;
        this.rutePerjalanan = rutePerjalanan;
        this.sisaKursi = sisaKursi;
    }

    public String getKodeKereta() {
        return kodeKereta;
    }

    public String getNamaKereta() {
        return namaKereta;
    }

    public String getRutePerjalanan() {
        return rutePerjalanan;
    }

    public int getSisaKursi() {
        return sisaKursi;
    }

    public void setSisaKursi(int sisaKursi) {
        this.sisaKursi = sisaKursi;
    }
}