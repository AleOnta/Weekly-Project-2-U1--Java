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

    public Book(long isbn, String t, Year y, int pn, String auth, String gen) {
        super(isbn, t, y, pn);
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

    public static String transformToString(Book b) {
        return b.ISBN_code + "@" + b.title + "@" + b.release_Y + "@" + b.pageNum + "@" + b.author + "@" + b.genre;
    }

    public static Book transformToBook(String[] stringBook) {
        long isbn = Long.parseLong(stringBook[0]);
        String title = stringBook[1];
        Year release = Year.parse(stringBook[2]);
        int pn = Integer.parseInt(stringBook[3]);
        String auth = stringBook[4];
        String gen = stringBook[5];
        return new Book(isbn, title, release, pn, auth, gen);
    }
}
