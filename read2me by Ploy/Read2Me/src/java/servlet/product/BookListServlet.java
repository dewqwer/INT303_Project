package servlet.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import jpa.controller.BookJpaController;
import jpa.model.Book;

public class BookListServlet extends HttpServlet {

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

        String saleGroup = request.getParameter("saleGroup");
        String category = request.getParameter("category");

        BookJpaController bookJpaCtrl = new BookJpaController(utx, emf);
        List<Book> bookList = bookJpaCtrl.findBookEntities();
        List<Book> books = null;

        if (saleGroup != null && saleGroup.trim().length() > 0) {
            saleGroup = saleGroup.toUpperCase();
            books = new ArrayList<>();

            if (bookList != null) {
                for (Book book : bookList) {
                    if (book.getSalegroup() != null) {
                        if (book.getSalegroup().toUpperCase().equals(saleGroup)) {
                            books.add(book);
                        }
                    }
                }
            }
            request.setAttribute("title", saleGroup.toUpperCase());
        }

        if (category != null && category.trim().length() > 0) {
            category = category.toUpperCase();
            books = new ArrayList<>();

            if (bookList != null) {
                for (Book book : bookList) {
                    if (book.getCategory().toUpperCase().equals(category)) {
                        books.add(book);
                    }
                }
            }

            request.setAttribute("title", category.toUpperCase());
        }

        request.setAttribute("books", books);
        getServletContext().getRequestDispatcher("/product/BookList.jsp").forward(request, response);

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
