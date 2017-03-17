/**
 * Created by Syukri Mullia Adil P on 3/17/2017.
 *
 * Class yang digunakan untuk membuat output berbentuk seperti tabel.
 * Credited to Sahil Muthoo, berdasarkan pertanyaan yang ia jawab di Stack Overflow.
 * URL pertanyaan: http://stackoverflow.com/questions/8154366/how-to-center-a-string-using-string-format
 * Profil Sahil di SO: http://stackoverflow.com/users/237033/sahil-muthoo
 *
 */

public class StringUtils {

    public static String center(String s, int size) {
        return center(s, size, ' ');
    }

    public static String center(int s, int size) {
        return center(s + "", size, ' ');
    }

    public static String center(String s, int size, char pad) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }

    public static String left(String s, int size) {
        return left(s, size, ' ');
    }

    public static String left(String s, int size, char pad) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }
}
