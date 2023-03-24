package LibraryCatalogue;

import lombok.Getter;
import java.time.Year;

@Getter
public class Magazine extends Article {
    private Periodicity period;

    public Magazine(String title, Year release, int pn, Periodicity per) {
        super(title, release, pn);
        period = per;
    }

    public Magazine(long isbn, String title, Year release, int pn, Periodicity per) {
        super(isbn, title, release, pn);
        period = per;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "ISBN_code=" + this.getISBN_code() +
                ", title=" + this.getTitle() +
                ", release_Y=" + this.getRelease_Y() +
                ", pageNum=" + this.getPageNum() +
                ", period=" + this.getPeriod() +
                '}';
    }

    public static String transformToString(Magazine m) {
        return m.getISBN_code() + "@" + m.getTitle() + "@" + m.getRelease_Y() + "@" + m.getPageNum() + "@" + m.getPeriod();
    }

    public static Magazine transformToMagazine(String[] stringMag) {
        long isbn = Long.parseLong(stringMag[0]);
        String title = stringMag[1];
        Year release = Year.parse(stringMag[2]);
        int pn = Integer.parseInt(stringMag[3]);
        Periodicity per = Periodicity.valueOf(stringMag[4]);
        return new Magazine(isbn, title, release, pn, per);
    }
}
