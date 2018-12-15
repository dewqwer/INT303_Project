/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import jpa.controller.BookJpaController;
import jpa.controller.ReviewJpaController;
import jpa.model.Book;
import jpa.model.Customer;
import jpa.model.Review;

/**
 *
 * @author Dew2018
 */
public class ReviewServlet extends HttpServlet {
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
            throws ServletException, IOException, Exception {
        String returnUrl=request.getParameter("returnUrl");
        String comment=request.getParameter("comment");
        String ratingStr=request.getParameter("rating");
        String isbn=request.getParameter("isbn");
        
        HttpSession session=request.getSession(false);
        session.getAttribute("user");
        
        if(returnUrl!=null){
            request.setAttribute("returnUrl", returnUrl);
        }
        
        if(ratingStr==null){
            request.getServletContext().getRequestDispatcher("/product/BookDetail.jsp").forward(request, response);
        }
        
        else{
            Customer customer=(Customer) session.getAttribute("user");
            int rating = Integer.valueOf(ratingStr);
            
            BookJpaController bookJpaController = new BookJpaController(utx, emf);
            Book b = bookJpaController.findBook(isbn);
            
            Review review=new Review(rating);
            review.setComment(comment);
            review.setIsbn(b);
            review.setCustomerid(customer);
        
            ReviewJpaController reviewJpaController=new ReviewJpaController(utx, emf);
            reviewJpaController.create(review);
            
            response.sendRedirect(returnUrl);
            
        }
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
            Logger.getLogger(ReviewServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ReviewServlet.class.getName()).log(Level.SEVERE, null, ex);
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
