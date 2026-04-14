package librarymanagmentsystem.models;

public class BookCopy {

    private String id;
    private Book book;
    private boolean available;

    public BookCopy(String id,Book book){
        this.id=id;
        this.book=book;
        this.available=true;
        book.addCopy(this);
    }

    public String getId() { return id; }
    public Book getBook() { return book; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

     @Override
    public String toString() {
            return id + " (" + (available ? "available" : "checked out") + ")";
    }
}
