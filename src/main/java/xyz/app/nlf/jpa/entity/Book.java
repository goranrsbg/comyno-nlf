
package xyz.app.nlf.jpa.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Lap
 */
@Entity
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence", allocationSize = 17)
@NamedQueries({
    @NamedQuery(name = "Books.all", query = "SELECT b FROM Book b ORDER BY b.name")
})
@Table(name = "books", indexes = {
    @Index(name = "book_name", columnList = "name", unique = true)
})
public class Book {
        
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    private long id;
    
    @Version
    private int version;
    
    @Length(min = 2, max = 255)
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    
    @Min(value = 0)
    @Column(name = "qty", columnDefinition = "integer default 0", nullable = false)
    private int quantity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private final Set<Loan> loans;
    
    public Book() {
        this("", 0);
    }
    
    public Book(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        loans = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<Loan> getLoans() {
        return loans;
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
        final Book other = (Book) obj;
        return id == other.getId()
                && quantity == other.getQuantity()
                && name.equals(other.getName());
    }

    @Override
    public String toString() {
        return String.format("ID%d %s (%d)", id, name, quantity);
    }
    
}
