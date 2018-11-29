package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.transaction.UserTransaction;
import jpa.controller.BookJpaController;
import jpa.model.Book;

public class WelcomepageProduct implements Filter {

    private FilterConfig filterConfig;

    @PersistenceUnit(unitName = "Read2MePU")
    EntityManagerFactory emf;

    @Resource
    UserTransaction utx;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        BookJpaController bookJpaCtrl = new BookJpaController(utx, emf);
        List<Book> bookList = bookJpaCtrl.findBookEntities();
        List<Book> books = new ArrayList<>();
        if (bookList != null) {
            for (Book book : bookList) {
                if ("NewRelease".equalsIgnoreCase(book.getSalegroup())) {
                    books.add(book);
                }
            }
        }
        request.setAttribute("books", books);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
