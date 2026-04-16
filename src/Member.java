
import java.util.List;

public class Member {
    public static Object loggedInMember;
    public String username;
    public List<Book> borrowedBooks;

    Member(String username) {
        this.username = username;
        this.borrowedBooks = new java.util.ArrayList<>();
    }
    public boolean borrowedBooks(Book book){
        if(borrowedBooks.size()>5 && book.isAvailable){
            borrowedBooks.add(book);
            book.isAvailable = false;
            return true;
        }
        return false;
    }

    public void returnBook(Book book){
        borrowedBooks.remove(book);
        book.isAvailable = true;
    }
}
