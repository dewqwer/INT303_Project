
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

public class SearchBookServlet extends HttpServlet {

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

        String keyword = request.getParameter("keyword");
        boolean isAdvanced = Boolean.parseBoolean(request.getParameter("advanced"));

        BookJpaController bookJpaCtrl = new BookJpaController(utx, emf);
        List<Book> bookList = bookJpaCtrl.findBookEntities();
        List<Book> books = null;
        String searchTerm = null;

        if (keyword != null && keyword.trim().length() > 0) {
            keyword = keyword.trim().toLowerCase();
            books = new ArrayList<>();
            searchTerm = keyword;

            if (bookList != null) {
                for (Book book : bookList) {
                    if (book.getTitle().toLowerCase().contains(keyword)) {
                        if (!books.contains(book)) {
                            books.add(book);
                        }
                    }
                    if (book.getAuthor().toLowerCase().contains(keyword)) {
                        if (!books.contains(book)) {
                            books.add(book);
                        }
                    }
                    if (book.getPublisher().toLowerCase().contains(keyword)) {
                        if (!books.contains(book)) {
                            books.add(book);
                        }
                    }
                    if (book.getIsbn().toLowerCase().contains(keyword)) {
                        if (!books.contains(book)) {
                            books.add(book);
                        }
                    }
                }
            }

        } else if (!isAdvanced) {
            request.setAttribute("title", "Advanced Search | Read2Me");
            request.getRequestDispatcher("/product/SearchBook.jsp").forward(request, response);
            return;
        }

        // กรณีเป็นการค้นหาแบบ Advanced
        if (isAdvanced) {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            String isbn = request.getParameter("isbn");
            String category = request.getParameter("category");

            // กรณีไม่มีการป้อน keyword ในการค้นหาแบบ Advanced
            if (books == null) {
                books = bookList;
            }

            if (books != null) {
                if (title != null && title.trim().length() > 0) {
                    title = title.trim().toLowerCase();
                    for (int i = 0; i < books.size(); i++) {
                        if (!books.get(i).getTitle().toLowerCase().contains(title)) {
                            books.remove(i);
                            i--;
                        }
                    }
                    searchTerm = searchTerm != null ? searchTerm : title;
                }
                if (author != null && author.trim().length() > 0) {
                    author = author.trim().toLowerCase();
                    for (int i = 0; i < books.size(); i++) {
                        if (!books.get(i).getAuthor().toLowerCase().contains(author)) {
                            books.remove(i);
                            i--;
                        }
                    }
                    searchTerm = searchTerm != null ? searchTerm : author;
                }
                
                if (publisher != null && publisher.trim().length() > 0) {
                    publisher = publisher.trim().toLowerCase();
                    for (int i = 0; i < books.size(); i++) {
                        if (!books.get(i).getPublisher().toLowerCase().contains(publisher)) {
                            books.remove(i);
                            i--;
                        }
                    }
                    searchTerm = searchTerm != null ? searchTerm : publisher;
                }
                if (isbn != null && isbn.trim().length() > 0) {
                    isbn = isbn.trim().toLowerCase();
                    for (int i = 0; i < books.size(); i++) {
                        if (!books.get(i).getIsbn().toLowerCase().contains(isbn)) {
                            books.remove(i);
                            i--;
                        }
                    }
                    searchTerm = searchTerm != null ? searchTerm : isbn;
                }
                if (category != null && category.trim().length() > 0) {
                    category = category.toLowerCase();
                    if (!category.equalsIgnoreCase("all")) {
                        for (int i = 0; i < books.size(); i++) {
                            if (!books.get(i).getCategory().equalsIgnoreCase(category)) {
                                books.remove(i);
                                i--;
                            }

                        }
                    }
                    searchTerm = searchTerm != null ? searchTerm : category;
                }
            }
        }

        request.setAttribute("books", books);
        request.setAttribute("searchTerm", searchTerm);
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
