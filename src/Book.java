import java.time.LocalData;

public class Book {
    
    String title;
    String author;
    String genre;
    boolean isAvailable;
    LocalDate dueDate;
    String borrowedby;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true; 
        this.dueDate=null;
        this.borrowedBy=null;
    }

}
