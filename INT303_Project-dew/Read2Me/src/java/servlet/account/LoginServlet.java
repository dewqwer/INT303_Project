package servlet.account;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import jpa.controller.CustomerJpaController;
import jpa.model.Customer;

public class LoginServlet extends HttpServlet {

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

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String returnUrl=request.getParameter("returnUrl");
        
        if(returnUrl!=null){
            request.setAttribute("returnUrl", returnUrl);
        }

        // กรณีมีการ login แล้วให้ทำการกลับไปหน้าหลัก
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect(returnUrl);
            return;
        }
        
        if (email != null && email.trim().length() > 0
                && password != null && password.trim().length() > 0) {

            String encryptedPassword = cryptWithMD5(password).substring(0, 19);

            CustomerJpaController customerJpaCtrl = new CustomerJpaController(utx, emf);
            List<Customer> listCustomer = customerJpaCtrl.findCustomerEntities();

            if (listCustomer != null) {
                for (Customer customer : listCustomer) {
                    if (email.equalsIgnoreCase(customer.getEmail()) && encryptedPassword.equals(customer.getPassword())) {
                        request.getSession().setAttribute("user", customer);
                        response.sendRedirect(returnUrl);
                        return;
                    }
                }
            }
            request.setAttribute("notice", "Email or password is incorrect.");
        }

        getServletContext().getRequestDispatcher("/account/Login.jsp").forward(request, response);

    }

    public static String cryptWithMD5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }
        return null;
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
