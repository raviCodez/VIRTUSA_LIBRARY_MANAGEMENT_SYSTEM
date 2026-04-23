import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {

    static Scanner scanner= new Scanner(System.in);
    static List <Book> books=new ArrayList<>();
    static List <Member> members=new ArrayList<>();
    static Member loggedInMember=null;
    static final String adminUsername="admin";
    static final String adminPassword="admin123";
        
    public static void main(String[] args) throws Exception {

        books.add(new Book("Java Programming", "John Doe", "Programming"));
        books.add(new Book("Python Programming", "Jane Doe", "Programming"));
        
        while(true){
            System.out.println("Welcome to the Library Management System");
            System.out.println("Enter role admin/user: ");

            String role =scanner.nextLine();

            if(role.equals("admin")){
                loginAsAdmin();
            }
            else if(role.equals("user")){
                longinAsUser();
            }
            else{
                System.out.println("Invalid role. Please try again.");
            }
        }
    }

    private static void loginAsAdmin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            System.out.println("Logged in as admin.");
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private static void longinAsUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
//checks for existing username in members , or else create new object        
        loggedInMember = findOrCreateMember(username);
        System.out.println("Logged in as user.");
        userMenu();
    }

    private static Member findOrCreateMember(String username){
        for(Member member : members){
            if(member.username.equals(username)){
                return member;
            }
        }
        Member mewMember = new Member(username);
        members.add(mewMember);
        return mewMember;
    }
     private static void adminMenu(){
        while (true){
            System.out.println("\nLibrary Management System - Admin Menu");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Add Member");
            System.out.println("5. Display Books");
            System.out.println("6. Display All Members");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch ((choice)) {
                case 1:
                    addBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    addMember();
                    break;
                case 5:
                    displayBooks();
                    break;
                case 6:
                    displayAllMembers();
                    break;
                case 7:
                    System.out.println("Exiting admin menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }

     }

     private static void userMenu(){
        while (true){
            System.out.println("\nLibrary Management System");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Display All Members");
            System.out.println("5. Search Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch ((choice)) {
                case 1:
                    borrowBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    displayBooks();
                    break;
                case 4:
                    displayAllMembers();
                    break;
                case 5:
                    searchBook();
                    break;

                case 6:
                    System.out.println("Exiting user menu.");
                    return;
              
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
     }

     private static void addBook(){
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book genre: ");
        String genre = scanner.nextLine();
        books.add(new Book(title, author, genre));
        System.out.println("Book added successfully.");
     }

     private static void updateBook(){
        System.out.print("Enter book title to update: ");
        String title=scanner.nextLine();

        Book book=findBookByTitle(title);

        if(book!=null){
            System.out.print("Enter new author: ");
            book.author=scanner.nextLine();
            System.out.print("Enter new genre: ");
            book.genre=scanner.nextLine();
            System.out.println("Book updated successfully.");


        }
        else{
            System.out.println("Book not found.");
        }
     }

     private static void deleteBook(){
        System.out.print("Enter book title to delete: ");
        String title=scanner.nextLine();

        Book book=findBookByTitle(title);

        if(book!=null){
            books.remove(book);
            System.out.println("Book deleted successfully.");
        }
        else{
            System.out.println("Book not found.");
        }
     }

     private static Book findBookByTitle(String title){
        for(Book book:books){
            if(book.title.equalsIgnoreCase(title)){
                return book;
            }
        }
        return null;
     }

     private static void addMember(){
        System.out.print("Enter member username: ");
        String username=scanner.nextLine();
        members.add(new Member(username));
        System.out.println("Member added successfully.");
     }

     private static void borrowBook(){
        System.out.print("Enter book title to borrow: ");
        String title=scanner.nextLine();

        Book book=findBookByTitle(title);

        if(book!=null && loggedInMember.borrowBooks(book)){
            System.out.println("Book borrowed successfully.");
        }
        else{
            System.out.println("Book not available for borrowing.");
        }
     }

     private static void returnBook(){
        System.out.print("Enter book title to return: ");
        String title=scanner.nextLine();

        Book book=findBookByTitle(title);

        if(book!=null && !book.isAvailable){
            loggedInMember.returnBook(book);
            System.out.println("Book returned successfully.");
        }
        else{
            System.out.println("Book not found in your borrowed list.");
        }
     }

     private static void displayBooks(){
        if(books.isEmpty()){
            System.out.println("No books available.");
        }
        else{
            System.out.println("Books in the library:");
            for(Book book:books){
                System.out.println(book.title+" by "+book.author+" - "+(book.isAvailable?"Available":"Not Available"));
            }
        }
     }

     private static void displayAllMembers(){
        if(members.isEmpty()){
            System.out.println("No members found.");
        }
        else{
            System.out.println("Library Members:");
            for(Member member:members){
                System.out.println(member.username);
            }
        }
     }


private static void searchBook() {
    System.out.println("Search by:");
    System.out.println("1. Title");
    System.out.println("2. Author");

    int choice = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Enter keyword: ");
    String keyword = scanner.nextLine().toLowerCase();

    boolean found = false;

    for (Book book : books) {
        if ((choice == 1 && book.title.toLowerCase().contains(keyword)) ||
            (choice == 2 && book.author.toLowerCase().contains(keyword))) {

            System.out.println(book.title + " by " + book.author +
                    " - " + (book.isAvailable ? "Available" : "Not Available"));
            found = true;
        }
    }

    if (!found) {
        System.out.println("No matching books found.");
    }
}
    

}
