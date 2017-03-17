import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * Created by Syukri Mullia Adil P on 3/17/2017.
 * Class yang me-drive jalannya turnamen CS League.
 */

public class Game {
    private final List<String> LIST_NAMA_TIM = Arrays.asList("Gajah", "Rusa", "Belalang", "Kodok", "Kucing",
            "Tupai", "Rajawali", "Siput", "Kumbang", "Semut", "Ular", "Harimau",
            "Kuda", "Serigala", "Panda", "Kadal", "Ayam", "Bebek");

    private final List<String> LIST_NAMA_PEMAIN = Arrays.asList("Arnold", "Kaidou", "Chopper", "Pica", "Enel",
            "Zoro", "Pedro", "Beckman", "Ace", "Shiryu", "Sakazuki", "Marco",
            "Garp", "Dadan", "Sengoku", "Sanji", "Magellan", "Dragon", "Sabo",
            "Smoker", "Luffy", "Franky", "Borsalino", "Buggy", "Crocodile",
            "Naruto", "Yasopp", "Coby", "Burgess", "Usopp", "Law", "Kid", "Bege",
            "Yonji", "Doffy", "Edward", "Mihawk", "Shanks", "Jinbei", "Killer",
            "Robin", "Roger", "Shiki", "Rayleigh", "Robb", "Kuma", "Moriah",
            "Teach", "Pagaya", "Conis", "Hachi", "Sasuke", "Kinemon", "Vergo",
            "Caesar", "Momo", "Mohji", "Cabaji", "Jozu", "Vista", "Doma", "Augur",
            "Drake", "Ivankov", "Charlotte", "Bellamy", "Demaro", "Dorry",
            "Brogy", "Kuro", "Zeff", "Gin", "Pearl", "Alvide", "Apoo", "Kuzan",
            "Nami", "Brook", "Hancock", "Koala");

    private final List<Integer> LIST_NOMOR_PEMAIN = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
            31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
            41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
            51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
            61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
            71, 72, 73, 74, 75, 76, 77, 78, 79, 80,
            81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
            91, 92, 93, 94, 95, 96, 97, 98, 99
    );

    private Liga liga;
    private boolean initialized = false;

    public boolean hasInitialized() {
        return initialized;
    }

    public boolean isFinished() {
        if (liga == null) {
            return false;
        }
        if (liga.isFinished()) {
            System.out.println("\nCS LEAGUE MUSIM INI TELAH USAI!");
            System.out.println("\nCHAMPION: Tim " + liga.getJuara());
            System.out.println("\nKLASEMEN AKHIR\n");
            showKlasemen();
            System.out.println("\nTOP SCORE\n");
            showPencetakGol();
            return true;
        }
        return false;
    }

    public boolean initGame(int jumlahTim) {
        if (initialized) {
            System.out.println("ERROR: Game sudah di-init, init gagal!");
            return false;
        }
        if (jumlahTim < 4) {
            System.out.println("ERROR: Jumlah tim minimal 4 tim.");
            return false;
        }
        initialized = true;

        Collections.shuffle(LIST_NAMA_TIM);
        Collections.shuffle(LIST_NAMA_PEMAIN);
        Collections.shuffle(LIST_NOMOR_PEMAIN);

        List<String> namaTim = LIST_NAMA_TIM.subList(0, jumlahTim);
        Tim[] tim = new Tim[jumlahTim];

        for (int i = 0; i < namaTim.size(); i++) {
            Pemain[] pemain = new Pemain[5];
            for (int j = 0; j < pemain.length; j++) {
                int no = LIST_NOMOR_PEMAIN.get(i * pemain.length + j);
                String nama = LIST_NAMA_PEMAIN.get(i * pemain.length + j);
                pemain[j] = new Pemain(no, nama, namaTim.get(i));
            }
            tim[i] = new Tim(namaTim.get(i), pemain);
        }
        liga = new Liga(tim);
        System.out.println("Init berhasil!");
        return true;
    }

    public void initLiga() {
        liga.acakPertandingan();
    }

    public void nextGameRandom() {
        Pertandingan p = liga.getPertandinganBerikutnya();
        Tim timSatu = p.getTimSatu();
        Tim timDua = p.getTimDua();
        Random r = new Random();

        int golTimSatu = r.nextInt(6);
        int golTimDua = r.nextInt(6);
        timSatu.cetakGolRandom(golTimSatu);
        timDua.cetakGolRandom(golTimDua);
        timSatu.kebobolan(golTimDua);
        timDua.kebobolan(golTimSatu);

        if (golTimDua > golTimSatu) {
            timDua.menang();
            timSatu.kalah();
        } else if (golTimSatu > golTimDua) {
            timSatu.menang();
            timDua.kalah();
        } else {
            timSatu.seri();
            timDua.seri();
        }

        int foulTimSatu = r.nextInt(6);
        int foulTimDua = r.nextInt(6);
        timSatu.pelanggaranRandom(foulTimSatu);
        timDua.pelanggaranRandom(foulTimDua);

        int kkTimSatu = r.nextInt(4);
        int kkTimDua = r.nextInt(4);
        timSatu.kartuKuningRandom(kkTimSatu);
        timDua.kartuKuningRandom(kkTimDua);

        int kmTimSatu = r.nextInt(3);
        int kmTimDua = r.nextInt(3);
        timSatu.kartuMerahRandom(kmTimSatu);
        timDua.kartuMerahRandom(kmTimDua);

        p.cetakStatistikPertandingan();
        liga.pertandinganBerikutnya();
    }

    public void nextGame(List<String> listArgumen) throws NoSuchElementException, NumberFormatException{
        Pertandingan p = liga.getPertandinganBerikutnya();
        Tim timSatu = p.getTimSatu();
        Tim timDua = p.getTimDua();

        for (int i = 0; i < listArgumen.size(); i += 3) {
            Tim timMain = null;
            Tim timLawan = null;
            String arg = listArgumen.get(i);
            String namaTim = listArgumen.get(i + 1);
            int nomorPemain = Integer.parseInt(listArgumen.get(i + 2));
            if (timSatu.getNamaTim().equals(namaTim)) {
                timMain = timSatu;
                timLawan = timDua;
            } else if (timDua.getNamaTim().equals(namaTim)) {
                timMain = timDua;
                timLawan = timSatu;
            } else {
                System.out.println("ERROR: Tim " + namaTim + " sedang tidak bertanding atau salah tim!");
                return;
            }

            if (arg.equalsIgnoreCase("-g")) {
                if (!timMain.cetakGol(nomorPemain)) {
                    System.out.println("ERROR: Tidak ada pemain nomor " + nomorPemain + " di Tim " + namaTim + "!");
                    return;
                }
                timLawan.kebobolan(1);
            } else if (arg.equalsIgnoreCase("-p")) {
                if (!timMain.pelanggaran(nomorPemain)) {
                    System.out.println("ERROR: Tidak ada pemain nomor " + nomorPemain + " di Tim " + namaTim + "!");
                    return;
                }
            } else if (arg.equalsIgnoreCase("-kk")) {
                if (!timMain.kartuKuning(nomorPemain)) {
                    System.out.println("ERROR: Tidak ada pemain nomor " + nomorPemain + " di Tim " + namaTim + "!");
                    return;
                }
            } else if (arg.equalsIgnoreCase("-km")) {
                if (!timMain.kartuMerah(nomorPemain)) {
                    System.out.println("ERROR: Tidak ada pemain nomor " + nomorPemain + " di Tim " + namaTim + "!");
                    return;
                }
            }
        }

        if (timDua.getGolPertandingan() > timSatu.getGolPertandingan()) {
            timDua.menang();
            timSatu.kalah();
        } else if (timSatu.getGolPertandingan() > timDua.getGolPertandingan()) {
            timSatu.menang();
            timDua.kalah();
        } else {
            timSatu.seri();
            timDua.seri();
        }
        p.cetakStatistikPertandingan();
        liga.pertandinganBerikutnya();
    }

    public void nextGameAll() {
        while(!liga.isFinished()) {
            nextGameRandom();
        }
    }

    public void showTim(String namaTim) {
        Tim tim = liga.cariTim(namaTim);
        if (tim != null) {
            tim.printShowTim();
        } else {
            System.out.println("ERROR: Tim " + namaTim + " tidak ditemukan!");
        }
    }

    public void showNextGame() {
        liga.checkPertandinganBerikutnya();
    }

    public void showKlasemen() {
        liga.printKlasemen();
    }

    public void showPencetakGol() {
        liga.printPencetakGol();
    }

    public void showPemain(String namaTim, String namaAtauNomorPemain) {
        Tim tim = liga.cariTim(namaTim);
        if (tim != null) {
            tim.showPemain(namaAtauNomorPemain);
        } else {
            System.out.println("ERROR: Tim " + namaTim + " tidak ditemukan!");
        }
    }
}
