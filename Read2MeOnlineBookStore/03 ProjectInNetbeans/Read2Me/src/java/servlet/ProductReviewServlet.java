/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import jpa.controller.BookJpaController;
import jpa.controller.ProductreviewJpaController;
import jpa.model.Book;
import jpa.model.Customer;
import jpa.model.Productreview;

/**
 *
 * @author Dew2018
 */
public class ProductReviewServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        HttpSession session = request.getSession(false);

        String reviewFromUser = request.getParameter("reviewFromUser");
        String ratingStr = request.getParameter("rating");
        String isbn = request.getParameter("isbn");

        System.out.println("reviewFromUser >>" + reviewFromUser);
        System.out.println("rating >>" + ratingStr);
        System.out.println("isbn >>" + isbn);

        if (isbn != null) {
            request.setAttribute("isbn", isbn);
        }

        System.out.println("isbn >>" + isbn);

        Customer customer = (Customer) session.getAttribute("user");
        System.out.println("customer >>" + customer);

        if (reviewFromUser != null && reviewFromUser.trim().length() > 0
                && ratingStr != null && ratingStr.trim().length() > 0) {
            int rating = Integer.valueOf(ratingStr);

            Productreview productReview = new Productreview();
            productReview.setComment(reviewFromUser);
            productReview.setRating(rating);
            productReview.setCustomerid(customer);

            BookJpaController bookJpaController = new BookJpaController(utx, emf);
            Book findBook = bookJpaController.findBook(isbn);

            productReview.setIsbn(findBook);

            ProductreviewJpaController productReviewJpaController = new ProductreviewJpaController(utx, emf);
            productReviewJpaController.create(productReview);
            getServletContext().getRequestDispatcher("/BookDetail").forward(request, response);
            return;
        }
        getServletContext().getRequestDispatcher("/reviewProduct.jsp").forward(request, response);
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
            Logger.getLogger(ProductReviewServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProductReviewServlet.class.getName()).log(Level.SEVERE, null, ex);
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
