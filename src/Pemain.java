/**
 * Created by Syukri Mullia Adil P on 3/17/2017.
 * Class yang merepresentasikan seorang pemain CS League.
 */

public class Pemain implements Comparable<Pemain>{
    private int nomerPemain;
    private String namaPemain;
    private String namaTim;
    private int jumlahGol;
    private int jumlahPelanggaran;
    private int jumlahKK;
    private int jumlahKM;

    public Pemain (int no, String nama, String t) {
        nomerPemain = no;
        namaPemain = nama;
        namaTim = t;
        jumlahGol = 0;
        jumlahPelanggaran = 0;
        jumlahKK = 0;
        jumlahKM = 0;
    }

    public void reset() {
        jumlahGol = 0;
        jumlahPelanggaran = 0;
        jumlahKK = 0;
        jumlahKM = 0;
    }

    public void cetakGol() {
        jumlahGol++;
    }

    public void pelanggaran() {
        jumlahPelanggaran++;
    }

    public void kartuKuning() {
        jumlahKK++;
        pelanggaran();
        if (jumlahKK % 2 == 0) {
            kartuMerah(false);
        }
    }

    public void kartuMerah(boolean kartuMerahLangsung) {
        jumlahKM++;
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

    public int getGolDicetak() {
        return jumlahGol;
    }

    public int getJumlahPelanggaran() {
        return jumlahPelanggaran;
    }

    public int getJumlahKartuKuning() {
        return jumlahKK;
    }

    public int getJumlahKartuMerah() {
        return jumlahKM;
    }

    public String toString() {
        return "Nomor: " + getNomorPemain() + "\n" +
                "Nama: " + getNamaPemain() + "\n" +
                "Gol: " + getGolDicetak() + "\n" +
                "Pelanggaran: " + getJumlahPelanggaran() + "\n" +
                "Kartu kuning: " + getJumlahKartuKuning() + "\n" +
                "Kartu merah: " + getJumlahKartuMerah();
    }

    @Override
    public int compareTo(Pemain pemainLain) {
        if (this.getGolDicetak() == pemainLain.getGolDicetak()) {
            if (this.getJumlahPelanggaran() == pemainLain.getJumlahPelanggaran()) {
                if (this.getJumlahKartuKuning() == pemainLain.getJumlahKartuKuning()) {
                    return this.getJumlahKartuMerah() - pemainLain.getJumlahKartuMerah();
                }
                return this.getJumlahKartuKuning() - pemainLain.getJumlahKartuKuning();
            }
            return this.getJumlahPelanggaran() - pemainLain.getJumlahPelanggaran();
        }
        return pemainLain.getGolDicetak() - this.getGolDicetak();
    }
}
