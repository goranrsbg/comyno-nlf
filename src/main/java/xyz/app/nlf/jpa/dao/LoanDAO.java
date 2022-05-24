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
public class LoanDAO {

    private static final LoanDAO INSTANCE = new LoanDAO();

    private final Logger LOGGER = LoggerFactory.getLogger(LoanDAO.class);

    private final SessionFactory SF = DBUtil.getSessionFactory();

    private LoanDAO() {
    }

    public static LoanDAO get() {
        return INSTANCE;
    }

    public List<Loan> readByStudent(Student student) {
        EntityManager em = SF.createEntityManager();
        List<Loan> loans = new ArrayList<>();
        try {
            em.getTransaction().begin();
            TypedQuery<Loan> query = em.createNamedQuery("Loans.student", Loan.class);
            query.setParameter("student", student);
            loans.addAll(query.getResultList());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error(e.getMessage(), e);
            write(e.getMessage());
        } finally {
            em.close();
        }
        return loans;
    }

    public void save(Loan loan) {
        EntityManager em = SF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(loan);
            write(String.format("Book %s, loaned by %s.", loan.getBook().getName(), loan.getStudent().getName()));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error(e.getMessage(), e);
            write(e.getMessage());
        } finally {
            em.close();
        }
    }

    public Loan update(Loan loan) {
        EntityManager em = SF.createEntityManager();
        Loan merged = null;
        try {
            em.getTransaction().begin();
            merged = em.merge(loan);
            write(String.format("Book %s, loaned by %s. Updated.", loan.getBook().getName(), loan.getStudent().getName()));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error(e.getMessage(), e);
            write(e.getMessage());
        } finally {
            em.close();
        }
        return merged == null ? loan : merged;
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
