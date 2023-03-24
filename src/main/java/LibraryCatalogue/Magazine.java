package LibraryCatalogue;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Year;

@Getter
public class Magazine extends Article {
    Periodicity period;

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
                "ISBN_code=" + ISBN_code +
                ", title=" + title +
                ", release_Y=" + release_Y +
                ", pageNum=" + pageNum +
                ", period=" + period +
                '}';
    }

    public static String transformToString(Magazine m) {
        return m.ISBN_code + "@" + m.title + "@" + m.release_Y + "@" + m.pageNum + "@" + m.period;
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
