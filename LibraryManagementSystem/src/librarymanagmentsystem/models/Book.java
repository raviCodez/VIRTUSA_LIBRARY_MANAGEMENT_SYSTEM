package librarymanagmentsystem.models;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private String id;
    private String title;
    private String author;

    private List<BookCopy> copies=new ArrayList<>();

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public void addCopy(BookCopy copy){
        copies.add(copy);
    }

    public String getId()     { return id; }
    public String getTitle()  { return title; }
    public String getAuthor() { return author; }
    public List<BookCopy> getCopies() { return copies; }

    public long getAvailableCount() {
        return copies.stream().filter(BookCopy::isAvailable).count();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s — %d available",
            id, title, author, getAvailableCount());
    }
}
