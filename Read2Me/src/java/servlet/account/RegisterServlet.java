
package servlet.account;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import jpa.controller.CustomerJpaController;
import jpa.model.Customer;

public class RegisterServlet extends HttpServlet {

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
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phone = request.getParameter("phone");

        // กรณีมีการลงชื่อเข้าใช้งานแล้วให้ทำการกลับไปหน้าหลัก
        if(request.getSession().getAttribute("user") != null){
            response.sendRedirect("/Read2Me");
            return;
        }
        
        if (email != null && email.trim().length() > 0
                && password != null && password.trim().length() > 0
                && firstname != null && firstname.trim().length() > 0
                && lastname != null && lastname.trim().length() > 0
                && phone != null && phone.trim().length() > 0) {

            try {
                CustomerJpaController customerJpaController = new CustomerJpaController(utx, emf);
                List<Customer> listCustomer = customerJpaController.findCustomerEntities();
                
                // เช็คอีเมลว่าซ้ำหรือไม่
                if (listCustomer != null) {
                    for (Customer c : listCustomer) {
                        if (email.equalsIgnoreCase(c.getEmail())) {
                            request.setAttribute("emailNotice", "Sorry, this email belongs to an existing account.");
                            getServletContext().getRequestDispatcher("/account/Register.jsp").forward(request, response);
                            return;
                        }
                    }
                }
                
                String encryptedPassword = cryptWithMD5(password).substring(0, 19);
                
                Customer customer = new Customer(firstname, lastname, phone, email, encryptedPassword);
                customerJpaController.create(customer);
                
                getServletContext().getRequestDispatcher("/welcomepage/index.jsp").forward(request, response);
                return;
                
            } catch (Exception ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        getServletContext().getRequestDispatcher("/account/Register.jsp").forward(request, response);

    }

    public static String cryptWithMD5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
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
