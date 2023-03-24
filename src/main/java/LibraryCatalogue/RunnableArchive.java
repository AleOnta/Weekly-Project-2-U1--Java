package LibraryCatalogue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Year;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class RunnableArchive {
    static final Logger log = LoggerFactory.getLogger(RunnableArchive.class);
    static final Scanner input = new Scanner(System.in);
    static Set<Article> archive = new HashSet<Article>();

    public static void main(String[] args) {

        // creating 2 Books & 2 Magazine
        Book b1 = new Book("Eragon", Year.parse("2007"), 865, "Christopher Paolini", "Fantasy");
        Book b2 = new Book("HarryPotter", Year.parse("1997"), 1021, "J.K. Rowling", "Fantasy");
        Magazine m1 = new Magazine("IO", Year.parse("2022"), 66, Periodicity.MONTHLY);
        Magazine m2 = new Magazine("TODAY", Year.parse("2020"), 38, Periodicity.WEEKLY);

        archive.add(b1);
        archive.add(b2);
        archive.add(m1);
        archive.add(m2);


        //addArticle();
        //removeArticleFromArchive();
        searchArticleByISBN();


    }

    public static void addArticle() {
        log.info("What kind of article would you like to add?\n0 - toExit the program\n1 - a Book\n2 - a Magazine");
        boolean isRunning = true;
        int pick = 4;
        while (isRunning) {
            if (input.hasNextInt()) {
                pick = input.nextInt();
                if (pick < 0 || pick > 3) {
                    log.info("invalid value, insert again");
                    input.nextLine();
                    continue;
                } else {
                    switch (pick) {
                        case 0 -> log.info("shutting off the system");
                        case 1 -> addToArchive(1);
                        case 2 -> addToArchive(2);
                    }
                }
            } else {
                log.warn("Unfit type of value, please insert an integer between 1 and 3");
                input.nextLine();
                continue;
            }
            isRunning = false;
        }
    }

    public static int askFor() {
        boolean isRunning = true;
        int pick = 0;
        while (isRunning) {
            if (input.hasNextInt()) {
                pick = input.nextInt();
            } else {
                log.warn("Unfit type of value, please insert an integer");
                input.nextLine();
                continue;
            }
            isRunning = false;
        }
        return pick;
    }

    public static long askFor(String type) {
        boolean isRunning = true;
        long pick = 0;
        while (isRunning) {
            if (input.hasNextLong()) {
                pick = input.nextLong();
            } else {
                log.warn("Unfit type of value, please insert a "+ type +" number");
                input.nextLine();
                continue;
            }
            isRunning = false;
        }
        return pick;
    }

    public static Periodicity askForPeriod() {
        boolean isRunning = true;
        Periodicity period = Periodicity.WEEKLY;
        int pick = 4;
        while (isRunning) {
            if (input.hasNextInt()) {
                pick = input.nextInt();
                if (pick < 0 || pick > 3) {
                    log.info("invalid value, insert again");
                    input.nextLine();
                    continue;
                } else {
                    switch (pick) {
                        case 1 -> period =  Periodicity.WEEKLY;
                        case 2 -> period = Periodicity.MONTHLY;
                        case 3 -> period = Periodicity.HALF_YEARLY;
                    }
                }
            } else {
                log.warn("Unfit type of value, please insert an integer between 1 and 3");
                input.nextLine();
                continue;
            }
            isRunning = false;
        }
        return period;
    }

    public static void printArticleISBN() {
        archive.forEach(article -> System.out.println("title: " + article.title + " - ISBN: " + article.ISBN_code));
    }

    public static void printArticleISBN(String s) {
        archive.forEach(article -> System.out.println(s + "- ISBN: " + article.ISBN_code));
    }

    public static void addToArchive(int n) {
        if (n == 1) {
            log.info("insert the title:");
            String title = input.next();
            log.info("insert the year:");
            String year = input.next();
            log.info("insert the number of page:");
            int pageNum = askFor();
            log.info("insert the author:");
            String auth = input.next();
            log.info("insert the genre:");
            String gen = input.next();
            log.info("Perfect, i'm adding the new Book!");
            archive.add(new Book(title, Year.parse(year), pageNum, auth, gen));
            log.info(archive.toString());
        } else {
            log.info("insert the title:");
            String title = input.next();
            log.info("insert the year:");
            String year = input.next();
            log.info("insert the number of page:");
            int pageNum = askFor();
            log.info("insert the corresponding value of periodicity: \n1 - Weekly | 2 - Monthly | 3 - Half Yearly");
            Periodicity period = askForPeriod();
            archive.add(new Magazine(title, Year.parse(year), pageNum, period));
            log.info(archive.toString());
        }
    }

    public static void removeArticleFromArchive() {
        log.info("Okay, let's remove an article from the archive");
        log.info("This is the current content of the archive:");
        printArticleISBN();
        log.info("Please, insert the ISBN of the book you would like to delete:");
        long pick = askFor("long");

        Set<Article> updatedArchive = archive.stream()
                .filter(article -> article.ISBN_code !=  pick)
                .collect(Collectors.toSet());
        archive.clear();
        archive.addAll(updatedArchive);
        log.info(archive.toString());
    }

    public static void searchArticleByISBN() {
        log.info("Okay, let's remove an article from the archive");
        log.info("This is the current content of the archive:");
        printArticleISBN("");
        log.info("Please, insert the ISBN of the book you would like to delete:");
        long pick = askFor("long");

        Article filtered = archive.stream()
                .filter(article -> article.ISBN_code == pick)
                .findFirst()
                .orElse(null);

        if (filtered == null) {
            log.warn("The ISBN you were searching doesn't exist in the archive.");
        } else {
            log.info(filtered.toString());
        }
    }
}
