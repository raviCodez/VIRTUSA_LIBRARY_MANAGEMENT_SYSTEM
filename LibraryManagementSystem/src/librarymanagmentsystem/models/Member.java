package librarymanagmentsystem.models;

import java.util.*;

public class Member {

    private  String id;
    private String name;
    private List<Loan> loans=new ArrayList<>();

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<Loan> getLoans() { return loans; }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    @Override
    public String toString() { return name + " (" + id + ")"; }
}
