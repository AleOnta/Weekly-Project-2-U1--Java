package LibraryCatalogue;

import lombok.Getter;
import java.time.Year;

@Getter
public class Book extends Article {
    private String author;
    private String genre;

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
                "ISBN_code=" + this.getISBN_code() +
                ", title=" + this.getTitle() +
                ", release_Y=" + this.getRelease_Y() +
                ", pageNum=" + this.getPageNum() +
                ", author=" + this.getAuthor() +
                ", genre=" + this.getGenre() +
                '}';
    }

    public static String transformToString(Book b) {
        return b.getISBN_code() + "@" + b.getTitle() + "@" + b.getRelease_Y() + "@" + b.getPageNum() + "@" + b.getAuthor() + "@" + b.getGenre();
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
