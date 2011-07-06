
/**
 *
 * @author Merter
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeyazOrumcek {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<String> ali = new ArrayList<String>();

        System.out.println("Yıl aralığı - Başlangıç (Dahil):");
        int y1 = s.nextInt();
        System.out.println("Yıl aralığı - Bitiş (Dahil):");
        int y2 = s.nextInt();
        if (y1 > 1900 && y1 <= y2) {
            Pattern p;
            Matcher m;
            for (int y = y1; y <= y2; y++) {
                int onyil = (y / 10) * 10;
                int page = 1, sayfa = 0;
                do {
                    String url = "http://www.beyazperde.com/filmler/tum-filmleri/beyazperde/onyil-" + onyil + "/yila-" + y + "/?page=" + page + "";
                    //
                    // Kaynak online alınmalı..
                    String source = new BeyazOrumcek().tempGrab();
                    //
                    //
                    /*Sayfaları listeler*/
                    String tempa = "";
                    p = Pattern.compile("(/?page=[\\d+]\">)([\\d+])(</a></li></ul>)");
                    m = p.matcher(source);
                    if (m.find()) {
                        sayfa = Integer.parseInt(m.group(2));
                    }

                    /*Filmleri listeler*/
                    String temp = "";
                    p = Pattern.compile("(<h2 class=\"tt_18 d_inline\"><a class=\"no_underline\" href=\"http://www.beyazperde.com/filmler/film-)([\\w]*)(/\">)(.*?)(</a></h2>)");
                    m = p.matcher(source);
                    while (m.find()) {
                        String film = m.group(2);
                        ali.add(film);
                        System.out.println(film);
                    }

                    System.out.println(y + " yılı , " + page + ". sayfa.");
                    page++;
                } while (page <= sayfa);

            }
        } else {
            System.out.println("Geçersiz yıl aralığı..");
        }
    }

    public String tempGrab() {
        try {
            Scanner s = new Scanner(new File("temp.html"), "UTF-8");
            String source = "";
            while (s.hasNext()) {
                source += s.nextLine();
            }
            return source;
        } catch (Exception e) {
            System.out.println("Dosya okunamadı." + e.getLocalizedMessage());
            return "-dosya okunamadı-";
        }
    }
}
