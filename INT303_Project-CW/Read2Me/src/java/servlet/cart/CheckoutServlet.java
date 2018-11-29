package servlet.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import jpa.controller.AddressJpaController;
import jpa.controller.CustomerJpaController;
import jpa.controller.LineitemJpaController;
import jpa.controller.OrdersJpaController;
import jpa.controller.PaymentJpaController;
import jpa.controller.ShippingJpaController;
import jpa.controller.exceptions.NonexistentEntityException;
import jpa.controller.exceptions.RollbackFailureException;
import jpa.model.Address;
import jpa.model.Customer;
import jpa.model.Lineitem;
import jpa.model.Orders;
import jpa.model.Payment;
import jpa.model.Shipping;
import model.ShoppingCart;

public class CheckoutServlet extends HttpServlet {

    @PersistenceUnit(unitName = "Read2MePU")
    EntityManagerFactory emf;

    @Resource
    UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String shippingAddress = request.getParameter("addressId");
        String shippingCost = request.getParameter("shippingCost");
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        Customer customer = (Customer) request.getSession().getAttribute("user");

        if (cart != null && customer != null) {
            if (shippingAddress != null && shippingAddress.trim().length() > 0
                    && shippingCost != null && shippingCost.trim().length() > 0) {
                Long addressId = Long.parseLong(shippingAddress);
                Double costOfShipping = Double.parseDouble(shippingCost);

                // Order
                OrdersJpaController ordersJpaCtrl = new OrdersJpaController(utx, emf);
                Orders order = new Orders(new Date(), "Pending");
                order.setCustomerid(customer);

                try {
                    ordersJpaCtrl.create(order);
                } catch (Exception ex) {
                    Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Lineitem
                LineitemJpaController lineItemJpaCtrl = new LineitemJpaController(utx, emf);
                List<Lineitem> lineItems = cart.getLineItems();
                List<Lineitem> lineItemList = new ArrayList<>();

                if (lineItems != null) {
                    for (Lineitem lineItem : lineItems) {
                        Lineitem Lineitem = new Lineitem(order, lineItem.getBook(), lineItem.getQuantity(), lineItem.getUnitprice());
                        lineItemList.add(Lineitem);
                        try {
                            lineItemJpaCtrl.create(Lineitem);
                        } catch (RollbackFailureException ex) {
                            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                // Payment
                PaymentJpaController paymentJpaCtrl = new PaymentJpaController(utx, emf);
                Payment payment = new Payment("Credit Card", cart.getTotalPrice() + costOfShipping);
                payment.setOrderid(order);

                try {
                    paymentJpaCtrl.create(payment);
                } catch (RollbackFailureException ex) {
                    Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Shipping
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DATE, 3);
                Date shippingDate = c.getTime();

                ShippingJpaController shippingJpaCtrl = new ShippingJpaController(utx, emf);
                Shipping shipping = new Shipping(costOfShipping, shippingDate, "Pending");
                shipping.setOrderid(order);

                AddressJpaController addressJpaCtrl = new AddressJpaController(utx, emf);
                Address address = addressJpaCtrl.findAddress(addressId);
                if (address != null) {
                    shipping.setAddressid(address);
                }

                try {
                    shippingJpaCtrl.create(shipping);
                } catch (RollbackFailureException ex) {
                    Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                order.setLineitemList(lineItemList);
                order.setPayment(payment);
                order.setShipping(shipping);

                try {
                    ordersJpaCtrl.edit(order);
                } catch (Exception ex) {
                    Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                List<Orders> orderList = customer.getOrdersList();
                if(orderList != null){
                    orderList.add(order);
                }
                
                CustomerJpaController customerJpaCtrl = new CustomerJpaController(utx, emf);
                try {
                    customerJpaCtrl.edit(customer);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RollbackFailureException ex) {
                    Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Clear cart
                request.getSession().setAttribute("cart", null);
                request.setAttribute("order", order);
                getServletContext().getRequestDispatcher("/cart/ConfirmOrder.jsp").forward(request, response);
                return;
            }
        }
        else{
            response.sendRedirect("/Read2Me");
        }
        getServletContext().getRequestDispatcher("/cart/Checkout.jsp").forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
//        getServletContext().getRequestDispatcher("/cart/Checkout.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
