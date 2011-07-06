
/**
 *
 * @author Merter
 */
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilmBilgileri {

    MashIt mit = new MashIt();
    String source;
    String url;
    boolean grabbed = false;

    public void grabSource(String url) {
        try {
            this.url = url;
            source = mit.mashIt(url);
            grabbed = true;
        } catch (Exception e) {
            url = "";
            grabbed = false;
        }
    }

    public String filmIsmi() {
        String temp;
        Pattern p = Pattern.compile("(<h1 property=\"v:name\">)(.*?)(</h1>)");
        Matcher m = p.matcher(source);
        if (m.find()) {
            temp = m.group(2);
        } else {
            temp = "-bulunamadı-";
        }
        return temp;
    }

    public String vizyonTarihi() {
        String temp;// = source.substring(source.indexOf("Vizyon tarihi:")+1);
        Pattern p = Pattern.compile("(Vizyon tarihi: <span class=\"bold\"><span class=\"acLnk [\\w\\s]*\">)(.*\\d{4})(</span></span>)");
        Matcher m = p.matcher(source);
        if (m.find()) {
            temp = m.group(2);
        } else {
            temp = "-bulunamadı-";
        }
        return temp;
    }

    public String yonetmen() {
//        String temp;

        //<a rel="v:directedBy" title="Lana Wachowski" href="/sanatcilar/sanatci-28989/" class="underline" >Lana Wachowski</a>

        String temp = "";
        Pattern p = Pattern.compile("(<a rel=\"v:directedBy\" title=\"[\\w\\s]*\" href=\"/sanatcilar/sanatci-[\\w]*/\" class=\"underline\" >)(.*?)(</a>)");
        Matcher m = p.matcher(source);
        while (m.find()) {
            temp += m.group(2) + ",";
        }

        return temp;
        /*
        Pattern p = Pattern.compile("(Yönetmen: <span class=\"bold\"><a .*>)([\\w\\s]*)(</a></span>)");
        Matcher m = p.matcher(source);
        if (m.find()) {
        temp = m.group(2);
        } else {
        temp = "-bulunamadı-";
        }
        return temp;
         */
    }

    public String orjinalAdi() {
        String temp;
        Pattern p = Pattern.compile("(Orijinal adı: <span class=\"purehtml\"><em>)(.*)(</em></span>)");
        Matcher m = p.matcher(source);
        if (m.find()) {
            temp = m.group(2);
        } else {
            temp = "-bulunamadı-";
        }
        return temp;
    }

    public String ulke() {
        String temp;
        Pattern p = Pattern.compile("(\\w*)(</span><span>\\. </span>)");
        Matcher m = p.matcher(source);
        if (m.find()) {
            temp = m.group(1);
        } else {
            temp = "-bulunamadı-";
        }
        return temp;
    }

    public String tur() {
        String temp = "";
        Pattern p = Pattern.compile("(<a class=\"underline\" href=\"/filmler/tum-filmleri/kullanici-puani/tur-[\\w]*/\">)(\\w*)(</a>)");
        Matcher m = p.matcher(source);
        while (m.find()) {
            temp += m.group(2) + ",";
        }

        return temp;
    }

    public String yapimYili() {
        String temp;
        Pattern p = Pattern.compile("(Yapım yılı:.*\">)(\\d{4})(</span>)");
        Matcher m = p.matcher(source);
        if (m.find()) {
            temp = m.group(2);
        } else {
            temp = "-bulunamadı-";
        }
        return temp;
    }

    public String ozet() {
        String temp;
        Pattern p = Pattern.compile("(<span class=\"bold\">Özet: </span><span property=\"v:summary\">)(.*?)(</span></p>)");
        Matcher m = p.matcher(source);
        if (m.find()) {
            temp = m.group(2);
        } else {
            temp = "-bulunamadı-";
        }
        return temp;
    }

    public ArrayList<String> oyuncular() {
        ArrayList<String> ali = new ArrayList<String>();
        String kaynak = mit.mashIt(url + "/oyuncular/");

        String temp = "";
        Pattern p = Pattern.compile("(<div class=\"titlebar\"><h3><a href=\"/sanatcilar/sanatci-[\\w]*?/\">)([\\w\\s\\f ]*?)(</a></h3></div><p>Rol:)");
        Matcher m = p.matcher(kaynak);
        while (m.find()) {
            String oyuncu = m.group(2);
            System.out.println("-" + oyuncu + "-");
            ali.add(oyuncu);
        }
//        System.out.println(temp);

//        System.out.println(kaynak);
        return ali;
    }

    public ArrayList<String> yilAraligindakiFilmler(int y1, int y2) {
        ArrayList<String> ali = new ArrayList<String>();
        
        
        
        return ali;
    }

    public static void main(String[] args) {
        FilmBilgileri bg = new FilmBilgileri();
        bg.grabSource("http://www.beyazperde.com/filmler/film-19776/");
        System.out.println(bg.filmIsmi());
        System.out.println(bg.vizyonTarihi());
        System.out.println(bg.yonetmen());
        System.out.println(bg.orjinalAdi());
        System.out.println(bg.ulke());
        System.out.println(bg.tur());
        System.out.println(bg.yapimYili());
        System.out.println(bg.ozet());
        bg.oyuncular();
    }
}
