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
import jpa.model.Address;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.controller.exceptions.IllegalOrphanException;
import jpa.controller.exceptions.NonexistentEntityException;
import jpa.controller.exceptions.RollbackFailureException;
import jpa.model.Customer;
import jpa.model.Orders;
import jpa.model.Review;

/**
 *
 * @author iMac
 */
public class CustomerJpaController implements Serializable {

    public CustomerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Customer customer) throws RollbackFailureException, Exception {
        if (customer.getAddressList() == null) {
            customer.setAddressList(new ArrayList<Address>());
        }
        if (customer.getOrdersList() == null) {
            customer.setOrdersList(new ArrayList<Orders>());
        }
        if (customer.getReviewList() == null) {
            customer.setReviewList(new ArrayList<Review>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Address> attachedAddressList = new ArrayList<Address>();
            for (Address addressListAddressToAttach : customer.getAddressList()) {
                addressListAddressToAttach = em.getReference(addressListAddressToAttach.getClass(), addressListAddressToAttach.getAddressid());
                attachedAddressList.add(addressListAddressToAttach);
            }
            customer.setAddressList(attachedAddressList);
            List<Orders> attachedOrdersList = new ArrayList<Orders>();
            for (Orders ordersListOrdersToAttach : customer.getOrdersList()) {
                ordersListOrdersToAttach = em.getReference(ordersListOrdersToAttach.getClass(), ordersListOrdersToAttach.getOrderid());
                attachedOrdersList.add(ordersListOrdersToAttach);
            }
            customer.setOrdersList(attachedOrdersList);
            List<Review> attachedReviewList = new ArrayList<Review>();
            for (Review reviewListReviewToAttach : customer.getReviewList()) {
                reviewListReviewToAttach = em.getReference(reviewListReviewToAttach.getClass(), reviewListReviewToAttach.getReviewid());
                attachedReviewList.add(reviewListReviewToAttach);
            }
            customer.setReviewList(attachedReviewList);
            em.persist(customer);
            for (Address addressListAddress : customer.getAddressList()) {
                Customer oldCustomeridOfAddressListAddress = addressListAddress.getCustomerid();
                addressListAddress.setCustomerid(customer);
                addressListAddress = em.merge(addressListAddress);
                if (oldCustomeridOfAddressListAddress != null) {
                    oldCustomeridOfAddressListAddress.getAddressList().remove(addressListAddress);
                    oldCustomeridOfAddressListAddress = em.merge(oldCustomeridOfAddressListAddress);
                }
            }
            for (Orders ordersListOrders : customer.getOrdersList()) {
                Customer oldCustomeridOfOrdersListOrders = ordersListOrders.getCustomerid();
                ordersListOrders.setCustomerid(customer);
                ordersListOrders = em.merge(ordersListOrders);
                if (oldCustomeridOfOrdersListOrders != null) {
                    oldCustomeridOfOrdersListOrders.getOrdersList().remove(ordersListOrders);
                    oldCustomeridOfOrdersListOrders = em.merge(oldCustomeridOfOrdersListOrders);
                }
            }
            for (Review reviewListReview : customer.getReviewList()) {
                Customer oldCustomeridOfReviewListReview = reviewListReview.getCustomerid();
                reviewListReview.setCustomerid(customer);
                reviewListReview = em.merge(reviewListReview);
                if (oldCustomeridOfReviewListReview != null) {
                    oldCustomeridOfReviewListReview.getReviewList().remove(reviewListReview);
                    oldCustomeridOfReviewListReview = em.merge(oldCustomeridOfReviewListReview);
                }
            }
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

    public void edit(Customer customer) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customer persistentCustomer = em.find(Customer.class, customer.getCustomerid());
            List<Address> addressListOld = persistentCustomer.getAddressList();
            List<Address> addressListNew = customer.getAddressList();
            List<Orders> ordersListOld = persistentCustomer.getOrdersList();
            List<Orders> ordersListNew = customer.getOrdersList();
            List<Review> reviewListOld = persistentCustomer.getReviewList();
            List<Review> reviewListNew = customer.getReviewList();
            List<String> illegalOrphanMessages = null;
            for (Address addressListOldAddress : addressListOld) {
                if (!addressListNew.contains(addressListOldAddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Address " + addressListOldAddress + " since its customerid field is not nullable.");
                }
            }
            for (Orders ordersListOldOrders : ordersListOld) {
                if (!ordersListNew.contains(ordersListOldOrders)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Orders " + ordersListOldOrders + " since its customerid field is not nullable.");
                }
            }
            for (Review reviewListOldReview : reviewListOld) {
                if (!reviewListNew.contains(reviewListOldReview)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Review " + reviewListOldReview + " since its customerid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Address> attachedAddressListNew = new ArrayList<Address>();
            for (Address addressListNewAddressToAttach : addressListNew) {
                addressListNewAddressToAttach = em.getReference(addressListNewAddressToAttach.getClass(), addressListNewAddressToAttach.getAddressid());
                attachedAddressListNew.add(addressListNewAddressToAttach);
            }
            addressListNew = attachedAddressListNew;
            customer.setAddressList(addressListNew);
            List<Orders> attachedOrdersListNew = new ArrayList<Orders>();
            for (Orders ordersListNewOrdersToAttach : ordersListNew) {
                ordersListNewOrdersToAttach = em.getReference(ordersListNewOrdersToAttach.getClass(), ordersListNewOrdersToAttach.getOrderid());
                attachedOrdersListNew.add(ordersListNewOrdersToAttach);
            }
            ordersListNew = attachedOrdersListNew;
            customer.setOrdersList(ordersListNew);
            List<Review> attachedReviewListNew = new ArrayList<Review>();
            for (Review reviewListNewReviewToAttach : reviewListNew) {
                reviewListNewReviewToAttach = em.getReference(reviewListNewReviewToAttach.getClass(), reviewListNewReviewToAttach.getReviewid());
                attachedReviewListNew.add(reviewListNewReviewToAttach);
            }
            reviewListNew = attachedReviewListNew;
            customer.setReviewList(reviewListNew);
            customer = em.merge(customer);
            for (Address addressListNewAddress : addressListNew) {
                if (!addressListOld.contains(addressListNewAddress)) {
                    Customer oldCustomeridOfAddressListNewAddress = addressListNewAddress.getCustomerid();
                    addressListNewAddress.setCustomerid(customer);
                    addressListNewAddress = em.merge(addressListNewAddress);
                    if (oldCustomeridOfAddressListNewAddress != null && !oldCustomeridOfAddressListNewAddress.equals(customer)) {
                        oldCustomeridOfAddressListNewAddress.getAddressList().remove(addressListNewAddress);
                        oldCustomeridOfAddressListNewAddress = em.merge(oldCustomeridOfAddressListNewAddress);
                    }
                }
            }
            for (Orders ordersListNewOrders : ordersListNew) {
                if (!ordersListOld.contains(ordersListNewOrders)) {
                    Customer oldCustomeridOfOrdersListNewOrders = ordersListNewOrders.getCustomerid();
                    ordersListNewOrders.setCustomerid(customer);
                    ordersListNewOrders = em.merge(ordersListNewOrders);
                    if (oldCustomeridOfOrdersListNewOrders != null && !oldCustomeridOfOrdersListNewOrders.equals(customer)) {
                        oldCustomeridOfOrdersListNewOrders.getOrdersList().remove(ordersListNewOrders);
                        oldCustomeridOfOrdersListNewOrders = em.merge(oldCustomeridOfOrdersListNewOrders);
                    }
                }
            }
            for (Review reviewListNewReview : reviewListNew) {
                if (!reviewListOld.contains(reviewListNewReview)) {
                    Customer oldCustomeridOfReviewListNewReview = reviewListNewReview.getCustomerid();
                    reviewListNewReview.setCustomerid(customer);
                    reviewListNewReview = em.merge(reviewListNewReview);
                    if (oldCustomeridOfReviewListNewReview != null && !oldCustomeridOfReviewListNewReview.equals(customer)) {
                        oldCustomeridOfReviewListNewReview.getReviewList().remove(reviewListNewReview);
                        oldCustomeridOfReviewListNewReview = em.merge(oldCustomeridOfReviewListNewReview);
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
                Long id = customer.getCustomerid();
                if (findCustomer(id) == null) {
                    throw new NonexistentEntityException("The customer with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customer customer;
            try {
                customer = em.getReference(Customer.class, id);
                customer.getCustomerid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customer with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Address> addressListOrphanCheck = customer.getAddressList();
            for (Address addressListOrphanCheckAddress : addressListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Customer (" + customer + ") cannot be destroyed since the Address " + addressListOrphanCheckAddress + " in its addressList field has a non-nullable customerid field.");
            }
            List<Orders> ordersListOrphanCheck = customer.getOrdersList();
            for (Orders ordersListOrphanCheckOrders : ordersListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Customer (" + customer + ") cannot be destroyed since the Orders " + ordersListOrphanCheckOrders + " in its ordersList field has a non-nullable customerid field.");
            }
            List<Review> reviewListOrphanCheck = customer.getReviewList();
            for (Review reviewListOrphanCheckReview : reviewListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Customer (" + customer + ") cannot be destroyed since the Review " + reviewListOrphanCheckReview + " in its reviewList field has a non-nullable customerid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(customer);
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

    public List<Customer> findCustomerEntities() {
        return findCustomerEntities(true, -1, -1);
    }

    public List<Customer> findCustomerEntities(int maxResults, int firstResult) {
        return findCustomerEntities(false, maxResults, firstResult);
    }

    private List<Customer> findCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customer.class));
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

    public Customer findCustomer(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customer> rt = cq.from(Customer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
