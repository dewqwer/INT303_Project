/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import jpa.controller.LineitemJpaController;
import jpa.controller.OrdersJpaController;
import jpa.controller.exceptions.RollbackFailureException;
import jpa.model.Customer;
import jpa.model.Lineitem;
import jpa.model.Orders;
import model.ShoppingCart;

/**
 *
 * @author Dew2018
 */
public class OrdersDetailsServlet extends HttpServlet {

    @PersistenceUnit(unitName = "Read2MeNovel")
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
            throws ServletException, IOException, RollbackFailureException, Exception {
        
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        Customer customer = (Customer) session.getAttribute("user");

        Orders orders = new Orders();
        orders.setOrdereddate(new Date());
        orders.setOrderstatus("ยังไม่ได้จ่ายเงิน");
        orders.setCustomerid(customer);

        OrdersJpaController ordersJpaController = new OrdersJpaController(utx, emf);
        ordersJpaController.create(orders);
        
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        
        List<Lineitem> lineItems = cart.getLineItems();
        List<Lineitem> myListItems = new ArrayList<>();

        LineitemJpaController lineitemJpaController = new LineitemJpaController(utx, emf);
        
        if(lineItems==null){
            System.out.println("lineItems is null.");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        for (Lineitem l : lineItems) {
            lineitemJpaController.create(l);

            if (l.getOrders().getCustomerid().getCustomerid() == customer.getCustomerid()) {
                myListItems.add(l);
            }
        }

        orders.setLineitemList(myListItems);

        session.setAttribute("orders", orders);
        
        getServletContext().getRequestDispatcher("/AddressShipping").forward(request, response);

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(OrdersDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(OrdersDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
