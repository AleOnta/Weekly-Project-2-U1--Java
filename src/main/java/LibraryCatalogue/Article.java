package LibraryCatalogue;

import java.time.LocalDate;
import java.util.Random;

public class Article {
    Random idGen = new Random();
    long ISBN_code;
    String title;
    LocalDate release_Y;
    int pageNum;

    public Article(String t, LocalDate release, int pn) {
        ISBN_code = generateID();
        title = t;
        release_Y = release;
        pageNum = pn;
    }

    private long  generateID() {
        return idGen.nextLong();
    }
}
