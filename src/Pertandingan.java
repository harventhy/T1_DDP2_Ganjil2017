/**
 * Created by Syukri Mullia Adil P on 3/17/2017.
 * Class yang merepresentasikan sebuah pertandingan CS League.
 */

public class Pertandingan {
    private Tim timSatu;
    private Tim timDua;

    public Pertandingan(Tim a, Tim b) {
        timSatu = a;
        timDua = b;
    }

    public Tim getTimSatu() {
        return timSatu;
    }

    public Tim getTimDua() {
        return timDua;
    }

    public void cetakStatistikPertandingan() {
        System.out.println("\nStatistik pertandingan Tim " + timSatu.getNamaTim() + " vs Tim " + timDua.getNamaTim());
        System.out.println("Tim: " + timSatu.getNamaTim());
        System.out.println("  Gol: " + timSatu.getGolPertandingan());
        System.out.println("  Pelanggaran: " + timSatu.getPelanggaranPertandingan());
        System.out.println("  Kartu kuning: " + timSatu.getKartuKuningPertandingan());
        System.out.println("  Kartu merah: " + timSatu.getKartuMerahPertandingan());

        System.out.println("Tim: " + timDua.getNamaTim());
        System.out.println("  Gol: " + timDua.getGolPertandingan());
        System.out.println("  Pelanggaran: " + timDua.getPelanggaranPertandingan());
        System.out.println("  Kartu kuning: " + timDua.getKartuKuningPertandingan());
        System.out.println("  Kartu merah: " + timDua.getKartuMerahPertandingan());

        timSatu.clearStatistikPertandingan();
        timDua.clearStatistikPertandingan();
    }

    public String toString() {
        return timSatu.getNamaTim() + " vs " + timDua.getNamaTim();
    }
}
