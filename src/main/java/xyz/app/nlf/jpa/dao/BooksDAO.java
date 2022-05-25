package xyz.app.nlf.jpa.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.app.nlf.jpa.DBUtil;
import xyz.app.nlf.jpa.entity.Book;
import xyz.app.nlf.jpa.entity.BooksCount;
import xyz.app.nlf.utils.SharedData;

/**
 *
 * @author Lap
 */
public class BooksDAO {

    private static final BooksDAO INSTANCE = new BooksDAO();

    private final Logger LOGGER = LoggerFactory.getLogger(BooksDAO.class);

    private final SessionFactory SF = DBUtil.getSessionFactory();

    private BooksDAO() {
    }

    public static BooksDAO get() {
        return INSTANCE;
    }

    public List<Book> readAll() {
        EntityManager em = SF.createEntityManager();
        List<Book> items = new ArrayList<>();
        try {
            em.getTransaction().begin();
            TypedQuery<Book> query = em.createNamedQuery("Books.all", Book.class);
            items.addAll(query.getResultList());
            em.getTransaction().commit();
            LOGGER.info("All Books:", items);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            write(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return items;
    }

    public void save(Book book) {
        EntityManager em = SF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            LOGGER.info("Saved Book:", book);
            write(String.format("Book %s successfuly saved.", book.getName()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            write(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Book update(Book book) {
        EntityManager em = SF.createEntityManager();
        Book merged = null;
        try {
            em.getTransaction().begin();
            merged = em.merge(book);
            em.getTransaction().commit();
            LOGGER.info("Updated Book:", merged);
            write(String.format("Book %s successfuly updated.", book.getName()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            write(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return merged == null ? book : merged;
    }

    public void delete(Book book) {
        EntityManager em = SF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(book);
            em.getTransaction().commit();
            LOGGER.info("Removed Book:", book);
            write(String.format("Book %s successfuly removed.", book.getName()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            write(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public BooksCount countBooks() {
        EntityManager em = SF.createEntityManager();
        BooksCount bc = new BooksCount();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("SELECT COUNT(b.qty) AS qty, COUNT(b.qty_loaned) AS qtyLoan FROM books b", "BookCount");
            bc = (BooksCount) query.getSingleResult();
            LOGGER.info("Loaded: " + bc);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            write(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return bc;
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
