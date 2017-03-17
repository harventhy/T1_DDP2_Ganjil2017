import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Syukri Mullia Adil P on 3/17/2017.
 * Class utama, terdapat method main di class ini.
 */

public class T1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Game game = new Game();

        System.out.println("SELAMAT DATANG!");
        while (!game.isFinished()) {
            System.out.print("[CSL2017] > ");
            String line = in.nextLine();
            StringTokenizer st = new StringTokenizer(line);

            String perintah = st.nextToken();

            if (perintah.equalsIgnoreCase("init")) {
                try {
                    int jumlahTim = Integer.parseInt(st.nextToken());
                    if (game.initGame(jumlahTim)) {
                        game.initLiga();
                    }
                } catch (NoSuchElementException E) {
                    System.out.println("ERROR: Argumen untuk perintah " + perintah + " tidak valid.");
                }
            } else if (game.hasInitialized()){
                if (perintah.equalsIgnoreCase("nextGame")) {
                    try {
                        if (st.hasMoreTokens()) {
                            String arg = st.nextToken();
                            if (arg.equalsIgnoreCase("-all")) {
                                game.nextGameAll();
                            } else {
                                List<String> listArgumen = new ArrayList<String>();
                                listArgumen.add(arg);
                                while (st.hasMoreTokens()) {
                                    listArgumen.add(st.nextToken());
                                }
                                if (listArgumen.size() % 3 != 0) {
                                    System.out.println("ERROR: Argumen untuk perintah " + perintah + " tidak valid.");
                                } else {
                                    game.nextGame(listArgumen);
                                }
                            }
                        } else {
                            game.nextGameRandom();
                        }
                    } catch (NoSuchElementException | NumberFormatException E) {
                        System.out.println("ERROR: Argumen untuk perintah " + perintah + " tidak valid.");
                    }
                } else if (perintah.equalsIgnoreCase("show")) {
                    try {
                        String perintah2 = st.nextToken();
                        if (perintah2.equalsIgnoreCase("tim")) {
                            game.showTim(st.nextToken());
                        } else if (perintah2.equalsIgnoreCase("nextGame")) {
                            game.showNextGame();
                        } else if (perintah2.equalsIgnoreCase("klasemen")) {
                            game.showKlasemen();
                        } else if (perintah2.equalsIgnoreCase("pencetakGol")) {
                            game.showPencetakGol();
                        } else if (perintah2.equalsIgnoreCase("pemain")) {
                            String namaTim = st.nextToken();
                            String namaAtauNomorPemain = st.nextToken();
                            game.showPemain(namaTim, namaAtauNomorPemain);
                        } else {
                            System.out.println("ERROR: Perintah tidak diketahui: " + perintah);
                        }
                    } catch (NoSuchElementException E) {
                        System.out.println("ERROR: Argumen untuk perintah " + perintah + " tidak valid.");
                    }
                } else {
                    System.out.println("ERROR: Perintah tidak diketahui: " + perintah);
                }
            } else {
                System.out.println("ERROR: Game belum di-init, silakan init terlebih dahulu dengan perintah: init [jumlahTim]");
            }
        }
        System.out.println("SAMPAI BERTEMU DI CS LEAGUE MUSIM BERIKUTNYA!");
    }
}
