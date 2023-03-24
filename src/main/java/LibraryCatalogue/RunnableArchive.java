package LibraryCatalogue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;

public class RunnableArchive {
    static final Logger log = LoggerFactory.getLogger(RunnableArchive.class);
    static final Scanner input = new Scanner(System.in);
    static Set<Article> archive = new HashSet<Article>();
    static final String path = "src\\main\\resources\\archive.txt";
    static String toWrite = "";

    public static void main(String[] args) throws IOException {
        initializeArchive();
        log.info("Welcome into the archive, what would you like to do?");
        boolean isRunning = true;
        while (isRunning) {
            log.info("");
            log.info("Digit in console the corresponding Integer to the action you want to perform.");
            log.info("1 - Add a new Article");
            log.info("2 - Delete an Article by ISBN");
            log.info("3 - Search for an Article");
            log.info("0 - To exit the System");

            int actionToPerform = askFor();
            switch (actionToPerform) {
                case 0 -> isRunning = false;
                case 1 -> addArticle();
                case 2 -> removeArticleFromArchive();
                case 3 -> generalSearch();
            }
        }

        log.info("Okay then, bye!\n");

        File f = new File(path);
        setToWrite();
        exportInFile(f, toWrite);
        uploadFromFile();
        for (Article a : archive) {
            log.info(a.toString());
        }

    }

    // method to intialize the archive with some hardcoded Articles
    public static void initializeArchive() {
        Book b1 = new Book("Eragon", Year.parse("2007"), 865, "Christopher Paolini", "Fantasy");
        Book b2 = new Book("HarryPotter", Year.parse("1997"), 1021, "J.K. Rowling", "Fantasy");
        Book b3= new Book("The Lord of the Rings", Year.parse("1937"), 1021, "J.K. Rowling", "Fantasy");
        Book b4 = new Book("The Brothers Karamazov", Year.parse("1880"), 1021, "Fyodor Dostoevsky", "Romance");
        Magazine m1 = new Magazine("IO", Year.parse("2022"), 66, Periodicity.MONTHLY);
        Magazine m2 = new Magazine("TODAY", Year.parse("2020"), 38, Periodicity.WEEKLY);
        Magazine m3 = new Magazine("MOTORS", Year.parse("2007"), 80, Periodicity.HALF_YEARLY);
        Magazine m4 = new Magazine("NOVELLA2000", Year.parse("2000"), 25, Periodicity.WEEKLY);

        archive.add(b1);
        archive.add(b2);
        archive.add(b3);
        archive.add(b4);
        archive.add(m1);
        archive.add(m2);
        archive.add(m3);
        archive.add(m4);
    }
    // method to ask for an integer in console by a scanner
    public static int askFor() {
        boolean isRunning = true;
        int pick = 0;
        while (isRunning) {
            if (input.hasNextInt()) {
                pick = input.nextInt();
                if (pick < 0 || pick > 3) {
                    log.info("invalid value, insert again");
                    input.nextLine();
                    continue;
                }
            } else {
                log.warn("Unfit type of value, please insert an integer");
                input.nextLine();
                continue;
            }
            isRunning = false;
        }
        return pick;
    }
    // overload of the previous method to ask an integer between 1 & 3
    public static int askFor(int i) {
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
    // overload of the previous method to ask for a string
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
    // method to ask to pick one of the 3 Enum available in Periodicity
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
    // method to perform research in the archive by ISBN CODE
    public static void printArticleISBN() {
        archive.forEach(article -> log.info("title: " + article.title + " - ISBN: " + article.ISBN_code));
        log.info("");
    }
    // method to print in console some articles value determined by the parameter passed as a string
    public static void printArticleInfo(String s) {
        if (s.equals("isbn")) {
            archive.forEach(article -> log.info("ISBN: " + article.ISBN_code));
            log.info("");
        } else if (s.equals("year")) {
            archive.forEach(article -> log.info("Year: " + article.release_Y));
            log.info("");
        } else {
            archive.stream()
                    .filter(article -> article instanceof Book )
                    .forEach(article -> log.info("Author: " + ((Book) article).author));
            log.info("");
        }

    }
    // method to initialize the process to add a new article in the archive
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
    // necessary method to continue the process of adding a new article in the archive
    public static void addToArchive(int n) {
        if (n == 1) {
            log.info("insert the title:");
            String title = input.next() + input.nextLine();
            log.info("insert the year:");
            String year = input.next();
            log.info("insert the number of page:");
            int pageNum = askFor(0);
            log.info("insert the author:");
            String auth = input.next() + input.nextLine();
            log.info("insert the genre:");
            String gen = input.next();
            log.info("Perfect, i'm adding the new Book!");
            archive.add(new Book(title, Year.parse(year), pageNum, auth, gen));
            log.info(archive.toString());
        } else {
            log.info("insert the title:");
            String title =input.next() + input.nextLine();
            log.info("insert the year:");
            String year = input.next();
            log.info("insert the number of page:");
            int pageNum = askFor(0);
            log.info("insert the corresponding value of periodicity: \n1 - Weekly | 2 - Monthly | 3 - Half Yearly");
            Periodicity period = askForPeriod();
            archive.add(new Magazine(title, Year.parse(year), pageNum, period));
            log.info(archive.toString());
        }
    }
    // method to  delete an article inside the archive via ISBN CODE
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
    // method to initialize the process of research of an article inside the archive
    public static void generalSearch() {
        log.info("What type of search you would like to perform?");
        log.info("Digit in the terminal the corresponding value:");
        log.info("1 - By ISBN | 2 - by Year | 3 - by Author");
        int pick = askFor();
        switch (pick) {
            case 1 -> {
                log.info("Okay, let's search in the archive by ISBN");
                log.info("This is the current content of the archive:");
                printArticleInfo("isbn");
                log.info("Please, insert the ISBN of the article you would like to delete:");
                long ISBN = askFor("long");
                searchArticleBy(ISBN);
            }
            case 2 -> {
                log.info("Okay, let's search in the archive by Year");
                log.info("This is the current content of the archive:");
                printArticleInfo("year");
                log.info("Please, insert the Year of the article you would like to delete:");
                String year = input.next();
                searchArticleBy(Year.parse(year));
            }
            case 3 -> {
                log.info("Okay, let's search in the archive by Author");
                log.info("This is the current content of the archive:");
                printArticleInfo("auth");
                log.info("Please, insert the Author of the article you would like to delete:");
                String auth = input.next() + input.nextLine();
                searchArticleBy(auth);
            }
        }
    }
    // research by ISBN
    public static void searchArticleBy(long n) {
        Article filtered = archive.stream()
                .filter(article -> article.ISBN_code == n)
                .findFirst()
                .orElse(null);

        if (filtered == null) {
            log.warn("SEARCH RESULT: The ISBN you were searching for doesn't exist in the archive.");
        } else {
            log.info("SEARCH RESULT: " + filtered.toString());
        }
    }
    //  method to perform research by Year - Overload
    public static void searchArticleBy(Year y) {
        Set<Article> filtered = archive.stream()
                .filter(article -> article.release_Y.equals(y))
                .collect(Collectors.toSet());
        if (filtered.size() < 1) {
            log.warn("SEARCH RESULT: The Year you were searching for doesn't exist in the archive.");
        } else {
            for (Article a : filtered) {
                log.info("SEARCH RESULT: " + a);
            }
        }
    }
    //  method to perform research by Author - Overload
    public static void searchArticleBy(String auth) {
        Set<Article> filtered = archive.stream()
                .filter(article -> article instanceof Book)
                .filter(article -> ((Book) article).author.contains(auth))
                .collect(Collectors.toSet());

        if (filtered.size() < 1) {
            log.warn("SEARCH RESULT: The Author you were searching for doesn't exist in the archive.");
        } else {
            for (Article a : filtered) {
                log.info("SEARCH RESULT: " + a);
            }
        }
    }
    // method to export the current archive in a txt file present in the resources project folder
    public static void exportInFile(File f, String s) {

        try {
            FileUtils.writeStringToFile(f, s, "UTF-8");
            log.info("Archive has been exported on file " + f.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    // method to transform all the archive into a string
    public static void setToWrite() {
        String updatedToWrite = "";
        for (Article a : RunnableArchive.archive) {
            if (a instanceof Book) {
                updatedToWrite += Book.transformToString((Book) a) + "#";
            } else {
                updatedToWrite += Magazine.transformToString((Magazine) a) + "#";
            }
        }
        RunnableArchive.toWrite =  updatedToWrite;
    }
    // method used to read the stringifyed archive retrieved into the txt file
    public static String readExistingFile(File f) {
        try {
            return ((FileUtils.readFileToString(f, "UTF-8")).replace(" | ", ", "));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    // method to import an archive from a txt file into project
    public static void uploadFromFile() throws IOException {
        archive.clear();
        File f = new File(path);
        String stringFile = FileUtils.readFileToString(f, "UTF-8");
        Set<String> stringArchive = new HashSet<String>(List.of(stringFile.split("#")));
        for (String s : stringArchive) {
            String[] toConvert = s.split("@");
            if (toConvert.length == 5) {
                archive.add(Magazine.transformToMagazine(toConvert));
            } else {
                archive.add(Book.transformToBook(toConvert));
            }
        }
        log.info("Archive has been successfully imported by archive.txt");
    }

}
