package LibraryCatalogue;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
public class Book extends Article {
    String author;
    String genre;

    public Book(String title, LocalDate release, int pn, String auth, String gen) {
        super(title, release, pn);
        author = auth;
        genre = gen;
    }
}
