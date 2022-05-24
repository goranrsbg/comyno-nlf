package xyz.app.nlf.jpa.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.app.nlf.jpa.DBUtil;
import xyz.app.nlf.jpa.entity.Loan;
import xyz.app.nlf.jpa.entity.Student;
import xyz.app.nlf.utils.SharedData;

/**
 *
 * @author Lap
 */
public class StudentsDAO {

    private static final StudentsDAO INSTANCE = new StudentsDAO();

    private final Logger LOGGER = LoggerFactory.getLogger(StudentsDAO.class);

    private final SessionFactory SF = DBUtil.getSessionFactory();

    private StudentsDAO() {
    }

    public static StudentsDAO get() {
        return INSTANCE;
    }

    public List<Student> readAll() {
        EntityManager em = SF.createEntityManager();
        List<Student> items = new ArrayList<>();
        try {
            em.getTransaction().begin();
            TypedQuery<Student> query = em.createNamedQuery("Students.all", Student.class);
            items.addAll(query.getResultList());
            em.getTransaction().commit();
            LOGGER.info("All Students:", items);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            write(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return items;
    }

    public void save(Student student) {
        EntityManager em = SF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            LOGGER.info("Saved Book:", student);
            write(String.format("Book %s successfuly saved.", student.getName()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            write(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Student update(Student student) {
        EntityManager em = SF.createEntityManager();
        Student merged = null;
        try {
            em.getTransaction().begin();
            merged = em.merge(student);
            em.getTransaction().commit();
            LOGGER.info("Updated Student:", merged);
            write(String.format("Student %s successfuly updated.", student.getName()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            write(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return merged == null ? student : merged;
    }

    public void delete(Student student) {
        EntityManager em = SF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(student);
            em.getTransaction().commit();
            LOGGER.info("Removed Student:", student);
            write(String.format("Student %s successfuly removed.", student.getName()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            write(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Show text on message label in primary view.
     *
     * @param text
     */
    private void write(String text) {
        SharedData.get().getPrimaryController().setMessageText(text);
    }

}
