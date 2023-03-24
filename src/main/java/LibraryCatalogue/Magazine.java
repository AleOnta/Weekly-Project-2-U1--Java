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
}
