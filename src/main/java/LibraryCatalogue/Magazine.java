package LibraryCatalogue;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class Magazine extends Article {
    Periodicity period;

    public Magazine(String title, LocalDate release, int pn, Periodicity per) {
        super(title, release, pn);
        period = per;
    }
}
