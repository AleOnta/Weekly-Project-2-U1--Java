package LibraryCatalogue;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Year;

@Getter
public class Book extends Article {
    String author;
    String genre;

    public Book(String title, Year release, int pn, String auth, String gen) {
        super(title, release, pn);
        author = auth;
        genre = gen;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN_code=" + ISBN_code +
                ", title=" + title +
                ", release_Y=" + release_Y +
                ", pageNum=" + pageNum +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }
}
