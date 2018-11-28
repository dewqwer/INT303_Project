/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import jpa.controller.BookJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import jpa.controller.ProductreviewJpaController;
import jpa.model.Book;
import jpa.model.Productreview;

/**
 *
 * @author User
 */
public class BookDetailServlet extends HttpServlet {
   @PersistenceUnit (unitName = "Read2MeNovel")
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
        String isbn = request.getParameter("isbn");
        String returnUrl = request.getParameter("returnUrl");
        System.out.println("BookDetailServlet >> returnUrl: "+returnUrl);
        
        if(returnUrl!=null){
            request.setAttribute("returnUrl", returnUrl);
        }                
        

        System.out.println("BookDetailServlet >> isbn: "+isbn);
        
        
        if(isbn == null){
          response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED);
        } else {
            BookJpaController bookJpaCtrl = new BookJpaController(utx, emf);
            Book books = bookJpaCtrl.findBook(isbn);
            request.setAttribute("books", books);
            
            ProductreviewJpaController productreviewJpaController=new ProductreviewJpaController(utx, emf);
            List<Productreview> productReviewList = productreviewJpaController.findProductreviewEntities();
            request.setAttribute("productReviewList", productReviewList);
            
            System.out.println("BookDetailServlet >> books.getIsbn(): "+books.getIsbn());
            System.out.println("BookDetailServlet >> productreViewList: "+productReviewList);
            
            response.sendRedirect(returnUrl);
            
//            getServletContext().getRequestDispatcher("/bookDetail.jsp").forward(request, response);
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
        processRequest(request, response);
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
