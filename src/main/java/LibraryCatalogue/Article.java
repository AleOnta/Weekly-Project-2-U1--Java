package LibraryCatalogue;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Year;
import java.util.Random;
@Getter
public class Article {
    Random idGen = new Random();
    long ISBN_code;
    String title;
    Year release_Y;
    int pageNum;

    public Article(String t, Year release, int pn) {
        ISBN_code = generateID();
        title = t;
        release_Y = release;
        pageNum = pn;
    }

    public Article(long isbn, String t, Year release, int pn) {
        ISBN_code = isbn;
        title = t;
        release_Y = release;
        pageNum = pn;
    }



    private long  generateID() {
        return Math.abs(idGen.nextLong());
    }
}
