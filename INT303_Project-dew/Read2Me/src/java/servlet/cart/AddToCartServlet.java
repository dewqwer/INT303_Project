package servlet.cart;

import java.io.IOException;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import jpa.controller.BookJpaController;
import jpa.model.Book;
import model.ShoppingCart;

public class AddToCartServlet extends HttpServlet {

    @PersistenceUnit(unitName = "Read2MePU")
    EntityManagerFactory emf;

    @Resource
    UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String isbn = request.getParameter("isbn");
        String returnUrl = request.getParameter("returnUrl");

        if (returnUrl != null) {
            request.setAttribute("returnUrl", returnUrl);
        }

        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            cart = new ShoppingCart();

            // เพื่อเก็บรายการสินค้าในตะกร้าจนกว่า user จะทำการ logout
            Cookie[] cookies = request.getCookies();
            for (Cookie ck : cookies) {
                ck.setMaxAge(60 * 60);
                response.addCookie(ck);
            }
        }

        BookJpaController bookJpaController = new BookJpaController(utx, emf);
        Book b = bookJpaController.findBook(isbn);
        cart.add(b);
        session.setAttribute("cart", cart);
        if (returnUrl != null) {
            response.sendRedirect(returnUrl);
        }else{
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
