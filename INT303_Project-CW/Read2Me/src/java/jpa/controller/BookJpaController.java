/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.model.Review;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.controller.exceptions.IllegalOrphanException;
import jpa.controller.exceptions.NonexistentEntityException;
import jpa.controller.exceptions.PreexistingEntityException;
import jpa.controller.exceptions.RollbackFailureException;
import jpa.model.Book;
import jpa.model.Lineitem;

/**
 *
 * @author iMac
 */
public class BookJpaController implements Serializable {

    public BookJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Book book) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (book.getReviewList() == null) {
            book.setReviewList(new ArrayList<Review>());
        }
        if (book.getLineitemList() == null) {
            book.setLineitemList(new ArrayList<Lineitem>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Review> attachedReviewList = new ArrayList<Review>();
            for (Review reviewListReviewToAttach : book.getReviewList()) {
                reviewListReviewToAttach = em.getReference(reviewListReviewToAttach.getClass(), reviewListReviewToAttach.getReviewid());
                attachedReviewList.add(reviewListReviewToAttach);
            }
            book.setReviewList(attachedReviewList);
            List<Lineitem> attachedLineitemList = new ArrayList<Lineitem>();
            for (Lineitem lineitemListLineitemToAttach : book.getLineitemList()) {
                lineitemListLineitemToAttach = em.getReference(lineitemListLineitemToAttach.getClass(), lineitemListLineitemToAttach.getLineitemPK());
                attachedLineitemList.add(lineitemListLineitemToAttach);
            }
            book.setLineitemList(attachedLineitemList);
            em.persist(book);
            for (Review reviewListReview : book.getReviewList()) {
                Book oldIsbnOfReviewListReview = reviewListReview.getIsbn();
                reviewListReview.setIsbn(book);
                reviewListReview = em.merge(reviewListReview);
                if (oldIsbnOfReviewListReview != null) {
                    oldIsbnOfReviewListReview.getReviewList().remove(reviewListReview);
                    oldIsbnOfReviewListReview = em.merge(oldIsbnOfReviewListReview);
                }
            }
            for (Lineitem lineitemListLineitem : book.getLineitemList()) {
                Book oldBookOfLineitemListLineitem = lineitemListLineitem.getBook();
                lineitemListLineitem.setBook(book);
                lineitemListLineitem = em.merge(lineitemListLineitem);
                if (oldBookOfLineitemListLineitem != null) {
                    oldBookOfLineitemListLineitem.getLineitemList().remove(lineitemListLineitem);
                    oldBookOfLineitemListLineitem = em.merge(oldBookOfLineitemListLineitem);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBook(book.getIsbn()) != null) {
                throw new PreexistingEntityException("Book " + book + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Book book) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Book persistentBook = em.find(Book.class, book.getIsbn());
            List<Review> reviewListOld = persistentBook.getReviewList();
            List<Review> reviewListNew = book.getReviewList();
            List<Lineitem> lineitemListOld = persistentBook.getLineitemList();
            List<Lineitem> lineitemListNew = book.getLineitemList();
            List<String> illegalOrphanMessages = null;
            for (Review reviewListOldReview : reviewListOld) {
                if (!reviewListNew.contains(reviewListOldReview)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Review " + reviewListOldReview + " since its isbn field is not nullable.");
                }
            }
            for (Lineitem lineitemListOldLineitem : lineitemListOld) {
                if (!lineitemListNew.contains(lineitemListOldLineitem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Lineitem " + lineitemListOldLineitem + " since its book field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Review> attachedReviewListNew = new ArrayList<Review>();
            for (Review reviewListNewReviewToAttach : reviewListNew) {
                reviewListNewReviewToAttach = em.getReference(reviewListNewReviewToAttach.getClass(), reviewListNewReviewToAttach.getReviewid());
                attachedReviewListNew.add(reviewListNewReviewToAttach);
            }
            reviewListNew = attachedReviewListNew;
            book.setReviewList(reviewListNew);
            List<Lineitem> attachedLineitemListNew = new ArrayList<Lineitem>();
            for (Lineitem lineitemListNewLineitemToAttach : lineitemListNew) {
                lineitemListNewLineitemToAttach = em.getReference(lineitemListNewLineitemToAttach.getClass(), lineitemListNewLineitemToAttach.getLineitemPK());
                attachedLineitemListNew.add(lineitemListNewLineitemToAttach);
            }
            lineitemListNew = attachedLineitemListNew;
            book.setLineitemList(lineitemListNew);
            book = em.merge(book);
            for (Review reviewListNewReview : reviewListNew) {
                if (!reviewListOld.contains(reviewListNewReview)) {
                    Book oldIsbnOfReviewListNewReview = reviewListNewReview.getIsbn();
                    reviewListNewReview.setIsbn(book);
                    reviewListNewReview = em.merge(reviewListNewReview);
                    if (oldIsbnOfReviewListNewReview != null && !oldIsbnOfReviewListNewReview.equals(book)) {
                        oldIsbnOfReviewListNewReview.getReviewList().remove(reviewListNewReview);
                        oldIsbnOfReviewListNewReview = em.merge(oldIsbnOfReviewListNewReview);
                    }
                }
            }
            for (Lineitem lineitemListNewLineitem : lineitemListNew) {
                if (!lineitemListOld.contains(lineitemListNewLineitem)) {
                    Book oldBookOfLineitemListNewLineitem = lineitemListNewLineitem.getBook();
                    lineitemListNewLineitem.setBook(book);
                    lineitemListNewLineitem = em.merge(lineitemListNewLineitem);
                    if (oldBookOfLineitemListNewLineitem != null && !oldBookOfLineitemListNewLineitem.equals(book)) {
                        oldBookOfLineitemListNewLineitem.getLineitemList().remove(lineitemListNewLineitem);
                        oldBookOfLineitemListNewLineitem = em.merge(oldBookOfLineitemListNewLineitem);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = book.getIsbn();
                if (findBook(id) == null) {
                    throw new NonexistentEntityException("The book with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Book book;
            try {
                book = em.getReference(Book.class, id);
                book.getIsbn();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The book with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Review> reviewListOrphanCheck = book.getReviewList();
            for (Review reviewListOrphanCheckReview : reviewListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Book (" + book + ") cannot be destroyed since the Review " + reviewListOrphanCheckReview + " in its reviewList field has a non-nullable isbn field.");
            }
            List<Lineitem> lineitemListOrphanCheck = book.getLineitemList();
            for (Lineitem lineitemListOrphanCheckLineitem : lineitemListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Book (" + book + ") cannot be destroyed since the Lineitem " + lineitemListOrphanCheckLineitem + " in its lineitemList field has a non-nullable book field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(book);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Book> findBookEntities() {
        return findBookEntities(true, -1, -1);
    }

    public List<Book> findBookEntities(int maxResults, int firstResult) {
        return findBookEntities(false, maxResults, firstResult);
    }

    private List<Book> findBookEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Book.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Book findBook(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }

    public int getBookCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Book> rt = cq.from(Book.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
