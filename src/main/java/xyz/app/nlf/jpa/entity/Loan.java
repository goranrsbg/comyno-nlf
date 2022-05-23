
package xyz.app.nlf.jpa.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author Lap
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Loans.all", query = "SELECT l FROM Loan l ORDER BY l.date")
})
@Table(name = "loans", indexes = {
    @Index(name = "loan_idx", columnList = "fk_book, fk_student", unique = true)
})
public class Loan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    private long id;
    
    @Version
    private int version;
    
    @Column(name = "date", length = 255, nullable = false)
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "fk_book")
    private Book book;
    
    @ManyToOne
    @JoinColumn(name = "fk_student")
    private Student student;
    
    @Column(name = "returned",columnDefinition = "boolean default false", nullable = false)
    private boolean returned;

    public Loan() {
    }

    public Loan(LocalDate date, Book book, Student student) {
        this.date = date;
        this.book = book;
        this.student = student;
        this.returned = false;
    }

    public long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
    
    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        final Loan other = (Loan) obj;
        return id == other.getId()
                && date.equals(other.getDate())
                && book.equals(other.getBook())
                && student.equals(other.getStudent())
                && returned == other.isReturned();
    }

    @Override
    public String toString() {
        return String.format("%d v%d - %s (%s) %s STATUS %s", id, version, book, student, date.toString(), returned ? "Not returned" : "Returned");
    }
    
    
}
