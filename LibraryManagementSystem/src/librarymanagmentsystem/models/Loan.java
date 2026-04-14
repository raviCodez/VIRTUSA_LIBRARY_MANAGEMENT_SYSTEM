package librarymanagmentsystem.models;

import java.time.LocalDate;

public class Loan {
    private BookCopy copy;
    private Member member;
    private LocalDate checkoutDate;
    private LocalDate dueDate;

    private static final int LOAN_PERIOD_DAYS = 14;
    private static final double FINE_PER_DAY   = 2.0;  

    public Loan(BookCopy copy, Member member) {
        this.copy = copy;
        this.member = member;
        this.checkoutDate = LocalDate.now();
        this.dueDate = checkoutDate.plusDays(LOAN_PERIOD_DAYS);
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate);
    }

    public double calculateFine() {
        if (!isOverdue()) return 0.0;
        long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(dueDate, LocalDate.now());
        return overdueDays * FINE_PER_DAY;
    }

    public BookCopy getCopy()       { return copy; }
    public Member getMember()       { return member; }
    public LocalDate getDueDate()   { return dueDate; }

    @Override
    public String toString() {
        return member.getName() + " → " + copy.getId() +
               " | due: " + dueDate + (isOverdue() ? " [OVERDUE]" : "");
    }
}