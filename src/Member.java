
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Member {
    public static Object loggedInMember;
    public String username;
    public List<Book> borrowedBooks;

    Member(String username) {
        this.username = username;
        this.borrowedBooks = new java.util.ArrayList<>();
    }
    public boolean borrowBooks(Book book){
        if(borrowedBooks.size()<5 && book.isAvailable){
            borrowedBooks.add(book);
            book.isAvailable = false;

            book.borrowedBy = this.username;

            book.dueDate = java.time.LocalDate.now().plusWeeks(2);

            System.out.println("Book borrowed successfully. Due date: " + book.dueDate);
            return true;
        }
        return false;
    }

    public void returnBook(Book book){
         LocalDate today = LocalDate.now();

    if (book.dueDate != null && today.isAfter(book.dueDate)) {

        long daysLate = ChronoUnit.DAYS.between(book.dueDate, today);
        long fine = daysLate * 2;

        System.out.println("Late by " + daysLate + " days.");
        System.out.println("Fine: ₹" + fine);
    }

    borrowedBooks.remove(book);
    book.isAvailable = true;
    book.dueDate = null;
    book.borrowedBy = null;
}
}
