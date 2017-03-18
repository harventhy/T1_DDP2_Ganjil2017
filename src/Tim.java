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
    private int kebobolan;
    private final int[] SHOW_TIM_PADDING = {7, 9, 5, 13, 14, 13};
    private String showTimHeader = "";

    public Tim(String nama, Pemain[] pemain) {
        namaTim = nama;
        this.pemain = pemain;
        showTimHeader = createShowTimHeader();
    }

    public String getNamaTim() {
        return namaTim;
    }

    public Pemain[] getDaftarPemain() {
        return pemain;
    }

    public void gol() {
        jumlahGol++;
    }

    public void kebobolan(int jumlahGol) {
        kebobolan += jumlahGol;
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
        int totalGol = 0;
        for (Pemain p : pemain) {
            totalGol += p.getGolPertandingan();
        }
        return totalGol;
    }

    public int getPelanggaranPertandingan() {
        int totalPelanggaran = 0;
        for (Pemain p : pemain) {
            totalPelanggaran += p.getPelanggaranPertandingan();
        }
        return totalPelanggaran;
    }

    public int getKartuKuningPertandingan() {
        int totalKK = 0;
        for (Pemain p : pemain) {
            totalKK += p.getKKPertandingan();
        }
        return totalKK;
    }

    public int getKartuMerahPertandingan() {
        int totalKM = 0;
        for (Pemain p : pemain) {
            totalKM += p.getKMPertandingan();
        }
        return totalKM;
    }

    public void applyStatistikPertandingan() {
        jumlahKebobolan += kebobolan;
        for (Pemain p : pemain) {
            p.applyPertandingan();
        }
        resetStatistikPertandingan();
    }

    public void resetStatistikPertandingan() {
        kebobolan = 0;
        for (Pemain p : pemain) {
            p.resetPertandingan();
        }
    }

    public Pemain getPemain(int nomorPemain) {
        for (Pemain p : pemain) {
            if (p.getNomorPemain() == nomorPemain) {
                return p;
            }
        }
        return null;
    }

    public boolean cetakGol(Pemain p) {
        if (!p.isKenaKartuMerah()) {
            p.cetakGol();
            gol();
            return true;
        }
        return false;
    }

    public void cetakGolRandom(int jumlahGol) {
        Random random = new Random();
        while (jumlahGol-- > 0) {
            int pencetakGolIdx = random.nextInt(5);
            Pemain pencetakGol = pemain[pencetakGolIdx];

            while(pencetakGol.isKenaKartuMerah()) {
                pencetakGolIdx = random.nextInt(5);
                pencetakGol = pemain[pencetakGolIdx];
            }
            cetakGol(pencetakGol);
        }
    }

    public boolean pelanggaran(Pemain p) {
        if (!p.isKenaKartuMerah()) {
            p.pelanggaran();
            return true;
        }
        return false;
    }

    public void pelanggaranRandom(int jumlahPelanggaran) {
        Random random = new Random();
        while (jumlahPelanggaran-- > 0) {
            int pelanggarIdx = random.nextInt(5);
            Pemain pelanggar = pemain[pelanggarIdx];

            while(pelanggar.isKenaKartuMerah()) {
                pelanggarIdx = random.nextInt(5);
                pelanggar = pemain[pelanggarIdx];
            }
            pelanggaran(pelanggar);
        }
    }

    public boolean kartuKuning(Pemain p) {
        if (!p.isKenaKartuMerah()) {
            p.kartuKuning();
            return true;
        }
        return false;
    }

    public void kartuKuningRandom(int jumlahKK) {
        Random random = new Random();
        while (jumlahKK-- > 0) {
            int kartuKuningIdx = random.nextInt(5);
            Pemain kk = pemain[kartuKuningIdx];

            while(kk.isKenaKartuMerah()) {
                kartuKuningIdx = random.nextInt(5);
                kk = pemain[kartuKuningIdx];
            }
            kartuKuning(kk);
        }
    }

    public boolean kartuMerah(Pemain p) {
        if (!p.isKenaKartuMerah()) {
            p.kartuMerah(true);
            return true;
        }
        return false;
    }

    public void kartuMerahRandom(int jumlahKM) {
        Random random = new Random();
        while (jumlahKM-- > 0) {
            int kartuMerahIdx = random.nextInt(5);
            Pemain km = pemain[kartuMerahIdx];

            while(km.isKenaKartuMerah()) {
                kartuMerahIdx = random.nextInt(5);
                km = pemain[kartuMerahIdx];
            }
            kartuMerah(km);
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
            String gol = StringUtils.center(p.getTotalGol(), SHOW_TIM_PADDING[2]);
            String foul = StringUtils.center(p.getTotalPelanggaran(), SHOW_TIM_PADDING[3]);
            String kk = StringUtils.center(p.getTotalKartuKuning(), SHOW_TIM_PADDING[4]);
            String km = StringUtils.center(p.getTotalKartuMerah(), SHOW_TIM_PADDING[5]);

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
