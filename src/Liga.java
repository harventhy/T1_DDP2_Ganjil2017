import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Syukri Mullia Adil P on 3/17/2017.
 * Class yang merepresentasikan keseluruhan turnamen CS League.
 */

public class Liga {
    private Tim[] tim;
    private Pemain[] pemain;
    private ArrayList<Pertandingan> pertandingan = new ArrayList<Pertandingan>();

    private final int[] SHOW_KLASEMEN_PADDING = {11, 10, 12, 18, 8, 7, 6, 6};
    private String showKlasemenHeader = "";

    private final int[] SHOW_PENCETAKGOL_PADDING = {11, 13, 10, 12};
    private String showPencetakGolHeader = "";

    public Liga(Tim[] tim) {
        this.tim = tim;
        this.pemain = new Pemain[tim.length * 5];
        for (int i = 0; i < tim.length; i++) {
            for (int j = 0; j < 5; j++) {
                pemain[i * 5 + j] = tim[i].getDaftarPemain()[j];
            }
        }
        this.showKlasemenHeader = createShowKlasemenHeader();
        this.showPencetakGolHeader = createShowPencetakGolHeader();
    }

    public Tim cariTim(String namaTim) {
        for (Tim t : tim) {
            if (t.getNamaTim().equals(namaTim)) {
                return t;
            }
        }
        return null;
    }

    public void acakPertandingan() {
        for (int i = 0; i < tim.length; i++) {
            for (int j = i + 1; j < tim.length; j++) {
                pertandingan.add(new Pertandingan(tim[i], tim[j]));
            }
        }
        Collections.shuffle(pertandingan);
    }

    public Pertandingan getPertandinganBerikutnya() {
        return pertandingan.get(0);
    }

    public boolean pertandinganBerikutnya() {
        if (!pertandingan.isEmpty()) {
            pertandingan.remove(0);
            return true;
        }
        return false;
    }

    public void lihatPertandinganBerikutnya() {
        System.out.println(pertandingan.get(0));
    }

    public boolean isFinished() {
        if (pertandingan.size() == 0) {
            Arrays.sort(tim);
            Arrays.sort(pemain);
            return true;
        }
        return false;
    }

    public String getJuara() {
        return tim[0].getNamaTim();
    }

    public void printKlasemen() {
        Arrays.sort(tim);
        System.out.println(showKlasemenHeader);
        for (int i = 0; i < tim.length; i++) {
            Tim t = tim[i];
            String peringkat = StringUtils.center(i + 1, SHOW_KLASEMEN_PADDING[0]);
            String nama = StringUtils.left(t.getNamaTim(), SHOW_KLASEMEN_PADDING[1]);
            String gol = StringUtils.center(t.getTotalGol(), SHOW_KLASEMEN_PADDING[2]);
            String kebobolan = StringUtils.center(t.getTotalKebobolan(), SHOW_KLASEMEN_PADDING[3]);
            String menang = StringUtils.center(t.getTotalMenang(), SHOW_KLASEMEN_PADDING[4]);
            String kalah = StringUtils.center(t.getTotalKalah(), SHOW_KLASEMEN_PADDING[5]);
            String seri = StringUtils.center(t.getTotalSeri(), SHOW_KLASEMEN_PADDING[6]);
            String poin = StringUtils.center(t.getPoin(), SHOW_KLASEMEN_PADDING[7]);

            String data = peringkat + " | " + nama + " | " + gol + " | " + kebobolan + " | " + menang + " | " + kalah +
                    " | " + seri + " | " + poin;
            System.out.println(data);
        }
    }

    public String createShowKlasemenHeader() {
        String peringkat = StringUtils.center("Peringkat", SHOW_KLASEMEN_PADDING[0]);
        String nama = StringUtils.center("Nama tim", SHOW_KLASEMEN_PADDING[1]);
        String gol = StringUtils.center("Jumlah gol", SHOW_KLASEMEN_PADDING[2]);
        String kebobolan = StringUtils.center("Jumlah kebobolan", SHOW_KLASEMEN_PADDING[3]);
        String menang = StringUtils.center("Menang", SHOW_KLASEMEN_PADDING[4]);
        String kalah = StringUtils.center("Kalah", SHOW_KLASEMEN_PADDING[5]);
        String seri = StringUtils.center("Seri", SHOW_KLASEMEN_PADDING[6]);
        String poin = StringUtils.center("Poin", SHOW_KLASEMEN_PADDING[7]);

        String header = peringkat + " | " + nama + " | " + gol + " | " + kebobolan + " | " + menang + " | " + kalah +
                " | " + seri + " | " + poin;
        String bottomBorder = "";
        int borderlength = header.length();
        while(borderlength-- > 0) {
            bottomBorder += "-";
        }
        return header + "\n" + bottomBorder;
    }

    public void printPencetakGol() {
        Arrays.sort(pemain);
        System.out.println(showPencetakGolHeader);
        for (int i = 0; i < 10; i++) {
            Pemain p = pemain[i];
            String peringkat = StringUtils.center(i + 1, SHOW_PENCETAKGOL_PADDING[0]);
            String pemain = StringUtils.left(p.getNamaPemain(), SHOW_PENCETAKGOL_PADDING[1]);
            String tim = StringUtils.left(p.getNamaTim(), SHOW_PENCETAKGOL_PADDING[2]);
            String gol = StringUtils.center(p.getTotalGol(), SHOW_PENCETAKGOL_PADDING[3]);

            String data = peringkat + " | " + pemain + " | " + tim + " | " + gol;
            System.out.println(data);
        }
    }

    public String createShowPencetakGolHeader() {
        String peringkat = StringUtils.center("Peringkat", SHOW_PENCETAKGOL_PADDING[0]);
        String pemain = StringUtils.center("Nama pemain", SHOW_PENCETAKGOL_PADDING[1]);
        String tim = StringUtils.center("Nama tim", SHOW_PENCETAKGOL_PADDING[2]);
        String gol = StringUtils.center("Jumlah gol", SHOW_PENCETAKGOL_PADDING[3]);

        String header = peringkat + " | " + pemain + " | " + tim + " | " + gol;
        String bottomBorder = "";
        int borderlength = header.length();
        while(borderlength-- > 0) {
            bottomBorder += "-";
        }
        return header + "\n" + bottomBorder;
    }
}
