
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
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Lap
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Students.all", query = "SELECT s FROM Student s ORDER BY s.name")
})
@Table(name = "students", indexes = {
    @Index(name = "student_name", columnList = "name")
})
public class Student {
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    private long id;
    
    @Version
    private int version;
    
    @Length(min = 2, max = 255)
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private final Set<Loan> loans;
    
    public Student() {
        this("");
    }
    public Student(String name) {
        this.name = name;
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
    
    public Set<Loan> getLoans() {
        return loans;
    }
    
    public String toCellString() {
        return String.format("ID%d %s", id, name);
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
        final Student other = (Student) obj;
        return id == other.getId()
                && name.equals(other.getName());
    }

    @Override
    public String toString() {
        return String.format("%d v%d - %s", id, version, name);
    }
      
}
