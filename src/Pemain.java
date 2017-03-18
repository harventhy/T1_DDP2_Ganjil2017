/**
 * Created by Syukri Mullia Adil P on 3/17/2017.
 * Class yang merepresentasikan seorang pemain CS League.
 */

public class Pemain implements Comparable<Pemain>{
    private int nomerPemain;
    private String namaPemain;
    private String namaTim;
    private int totalGol = 0;
    private int totalPelanggaran = 0;
    private int totalKK = 0;
    private int totalKM = 0;
    private boolean isKenaKM = false;
    private int golPertandingan = 0;
    private int pelanggaranPertandingan = 0;
    private int kkPertandingan = 0;
    private int kmPertandingan = 0;

    public Pemain (int no, String nama, String t) {
        nomerPemain = no;
        namaPemain = nama;
        namaTim = t;
    }

    public void cetakGol() {
        golPertandingan++;
    }

    public void pelanggaran() {
        pelanggaranPertandingan++;
    }

    public void kartuKuning() {
        kkPertandingan++;
        pelanggaran();
        if (kkPertandingan % 2 == 0) {
            kartuMerah(false);
        }
    }

    public void kartuMerah(boolean kartuMerahLangsung) {
        kmPertandingan++;
        isKenaKM = true;
        if (kartuMerahLangsung) {
            pelanggaran();
        }
    }

    public int getNomorPemain() {
        return nomerPemain;
    }

    public String getNamaPemain() {
        return namaPemain;
    }

    public String getNamaTim() {
        return namaTim;
    }

    public int getTotalGol() {
        return totalGol;
    }

    public int getTotalPelanggaran() {
        return totalPelanggaran;
    }

    public int getTotalKartuKuning() {
        return totalKK;
    }

    public int getTotalKartuMerah() {
        return totalKM;
    }

    public int getGolPertandingan() {
        return golPertandingan;
    }

    public int getPelanggaranPertandingan() {
        return pelanggaranPertandingan;
    }

    public int getKKPertandingan() {
        return kkPertandingan;
    }

    public int getKMPertandingan() {
        return kmPertandingan;
    }

    public boolean isKenaKartuMerah() {
        return isKenaKM;
    }

    public void applyPertandingan() {
        totalGol += golPertandingan;
        totalPelanggaran += pelanggaranPertandingan;
        totalKK += kkPertandingan;
        totalKM += kmPertandingan;
    }

    public void resetPertandingan() {
        golPertandingan = 0;
        pelanggaranPertandingan = 0;
        kkPertandingan = 0;
        kkPertandingan = 0;
        isKenaKM = false;
    }

    public String toString() {
        return "Nomor: " + getNomorPemain() + "\n" +
                "Nama: " + getNamaPemain() + "\n" +
                "Gol: " + getTotalGol() + "\n" +
                "Pelanggaran: " + getTotalPelanggaran() + "\n" +
                "Kartu kuning: " + getTotalKartuKuning() + "\n" +
                "Kartu merah: " + getTotalKartuMerah();
    }

    @Override
    public int compareTo(Pemain pemainLain) {
        if (this.getTotalGol() == pemainLain.getTotalGol()) {
            if (this.getTotalPelanggaran() == pemainLain.getTotalPelanggaran()) {
                if (this.getTotalKartuKuning() == pemainLain.getTotalKartuKuning()) {
                    return this.getTotalKartuMerah() - pemainLain.getTotalKartuMerah();
                }
                return this.getTotalKartuKuning() - pemainLain.getTotalKartuKuning();
            }
            return this.getTotalPelanggaran() - pemainLain.getTotalPelanggaran();
        }
        return pemainLain.getTotalGol() - this.getTotalGol();
    }
}
