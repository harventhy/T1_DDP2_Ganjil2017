import java.util.Random;

/**
 * Created by Syukri Mullia Adil P on 3/17/2017.
 * Class yang merepresentasikan sebuah Tim CS League.
 */

public class Tim implements Comparable<Tim> {
    private String namaTim;
    private Pemain[] pemain;
    private int jumlahMenang;
    private int jumlahKalah;
    private int jumlahSeri;
    private int jumlahGol;
    private int jumlahKebobolan;
    private int[] statistikPertandingan;

    private final int[] SHOW_TIM_PADDING = {7, 9, 5, 13, 14, 13};
    private String showTimHeader = "";

    public Tim(String nama, Pemain[] pemain) {
        namaTim = nama;
        this.pemain = pemain;
        showTimHeader = createShowTimHeader();
        statistikPertandingan = new int[4]; // {gol, foul, kk, km}
    }

    public String getNamaTim() {
        return namaTim;
    }

    public Pemain[] getPemain() {
        return pemain;
    }

    public void gol() {
        jumlahGol++;
    }

    public void kebobolan(int jumlahGol) {
        jumlahKebobolan += jumlahGol;
    }

    public void menang() {
        jumlahMenang++;
    }

    public void kalah() {
        jumlahKalah++;
    }

    public void seri() {
        jumlahSeri++;
    }

    public int getTotalGol() {
        return jumlahGol;
    }

    public int getTotalKebobolan() {
        return jumlahKebobolan;
    }

    public int getTotalMenang() {
        return jumlahMenang;
    }

    public int getTotalKalah() {
        return jumlahKalah;
    }

    public int getTotalSeri() {
        return jumlahSeri;
    }

    public int getTotalSelisihGol() {
        return getTotalGol() - getTotalKebobolan();
    }

    public int getPoin() {
        return 3 * getTotalMenang() + getTotalSeri();
    }

    public int getGolPertandingan() {
        return statistikPertandingan[0];
    }

    public int getPelanggaranPertandingan() {
        return statistikPertandingan[1];
    }

    public int getKartuKuningPertandingan() {
        return statistikPertandingan[2];
    }

    public int getKartuMerahPertandingan() {
        return statistikPertandingan[3];
    }

    public void clearStatistikPertandingan() {
        statistikPertandingan = new int[4];
    }

    public boolean cetakGol(int nomorPemain) {
        for (Pemain p : getPemain()) {
            if (p.getNomorPemain() == nomorPemain) {
                p.cetakGol();
                gol();
                statistikPertandingan[0]++;
                return true;
            }
        }
        return false;
    }

    public void cetakGolRandom(int jumlahGol) {
        Random random = new Random();
        while (jumlahGol-- > 0) {
            int pencetakGolIdx = random.nextInt(5);
            Pemain pencetakGol = pemain[pencetakGolIdx];
            pencetakGol.cetakGol();
            gol();
            statistikPertandingan[0]++;
        }
    }

    public boolean pelanggaran(int nomorPemain) {
        for (Pemain p : getPemain()) {
            if (p.getNomorPemain() == nomorPemain) {
                p.pelanggaran();
                statistikPertandingan[1]++;
                return true;
            }
        }
        return false;
    }

    public void pelanggaranRandom(int jumlahPelanggaran) {
        Random random = new Random();
        while (jumlahPelanggaran-- > 0) {
            int pelanggarIdx = random.nextInt(5);
            Pemain pencetakGol = pemain[pelanggarIdx];
            pencetakGol.pelanggaran();
            statistikPertandingan[1]++;
        }
    }

    public boolean kartuKuning(int nomorPemain) {
        for (Pemain p : getPemain()) {
            if (p.getNomorPemain() == nomorPemain) {
                p.kartuKuning();
                statistikPertandingan[1]++;
                statistikPertandingan[2]++;
                return true;
            }
        }
        return false;
    }

    public void kartuKuningRandom(int jumlahKK) {
        Random random = new Random();
        while (jumlahKK-- > 0) {
            int kartuKuningIdx = random.nextInt(5);
            Pemain pencetakGol = pemain[kartuKuningIdx];
            pencetakGol.kartuKuning();
            statistikPertandingan[1]++;
            statistikPertandingan[2]++;
        }
    }

    public boolean kartuMerah(int nomorPemain) {
        for (Pemain p : getPemain()) {
            if (p.getNomorPemain() == nomorPemain) {
                p.kartuMerah(true);
                statistikPertandingan[1]++;
                statistikPertandingan[3]++;
                return true;
            }
        }
        return false;
    }

    public void kartuMerahRandom(int jumlahKM) {
        Random random = new Random();
        while (jumlahKM-- > 0) {
            int kartuMerahIdx = random.nextInt(5);
            Pemain pencetakGol = pemain[kartuMerahIdx];
            pencetakGol.kartuMerah(true);
            statistikPertandingan[1]++;
            statistikPertandingan[3]++;
        }
    }

    public void showPemain(String namaAtauNomorPemain) {
        try {
            int nomorPemain = Integer.parseInt(namaAtauNomorPemain);
            for (Pemain p : pemain) {
                if (p.getNomorPemain() == nomorPemain) {
                    System.out.println(p);
                    return;
                }
            }
            System.out.println("ERROR: Pemain dengan nomor " + nomorPemain + " bukan anggota dari tim " + namaTim + "!");
        } catch (NumberFormatException E) {
            for (Pemain p : pemain) {
                if (p.getNamaPemain().equals(namaAtauNomorPemain)) {
                    System.out.println(p);
                    return;
                }
            }
            System.out.println("ERROR: Pemain dengan nama " + namaAtauNomorPemain + " bukan anggota dari tim " + namaTim + "!");
        }

    }

    public void printShowTim() {
        System.out.println(showTimHeader);

        for (Pemain p : pemain) {
            String nomor = StringUtils.center(p.getNomorPemain(), SHOW_TIM_PADDING[0]);
            String nama = StringUtils.left(p.getNamaPemain(), SHOW_TIM_PADDING[1]);
            String gol = StringUtils.center(p.getGolDicetak(), SHOW_TIM_PADDING[2]);
            String foul = StringUtils.center(p.getJumlahPelanggaran(), SHOW_TIM_PADDING[3]);
            String kk = StringUtils.center(p.getJumlahKartuKuning(), SHOW_TIM_PADDING[4]);
            String km = StringUtils.center(p.getJumlahKartuMerah(), SHOW_TIM_PADDING[5]);

            String data = nomor + " | " + nama + " | " + gol + " | " + foul + " | " + kk + " | " + km;
            System.out.println(data);
        }
    }

    public String createShowTimHeader() {
        String nomor = StringUtils.center("Nomor", SHOW_TIM_PADDING[0]);
        String nama = StringUtils.center("Nama", SHOW_TIM_PADDING[1]);
        String gol = StringUtils.center("Gol", SHOW_TIM_PADDING[2]);
        String foul = StringUtils.center("Pelanggaran", SHOW_TIM_PADDING[3]);
        String kk = StringUtils.center("Kartu kuning", SHOW_TIM_PADDING[4]);
        String km = StringUtils.center("Kartu merah", SHOW_TIM_PADDING[5]);

        String header = nomor + " | " + nama + " | " + gol + " | " + foul + " | " + kk + " | " + km;
        String bottomBorder = "";
        int borderlength = header.length();
        while(borderlength-- > 0) {
            bottomBorder += "-";
        }
        return header + "\n" + bottomBorder;
    }

    public int compareTo(Tim timLain) {
        if (this.getPoin() == timLain.getPoin()) {
            if (this.getTotalSelisihGol() == timLain.getTotalSelisihGol()) {
                if (this.getTotalGol() == timLain.getTotalGol()) {
                    return this.getTotalKebobolan() - timLain.getTotalKebobolan();
                }
                return timLain.getTotalGol() - this.getTotalGol();
            }
            return timLain.getTotalSelisihGol() - this.getTotalSelisihGol();
        }
        return timLain.getPoin() - this.getPoin();
    }
}
