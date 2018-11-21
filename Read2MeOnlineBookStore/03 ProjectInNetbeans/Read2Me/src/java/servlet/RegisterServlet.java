/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import jpa.controller.AddressJpaController;
import jpa.controller.CustomerJpaController;
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
import jpa.model.Address;
import jpa.model.Customer;

/**
 *
 * @author Dew2018
 */
public class RegisterServlet extends HttpServlet {

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");

        if (email != null && email.trim().length() > 0
                && password != null && password.trim().length() > 0
                && firstName != null && firstName.trim().length() > 0
                && lastName != null && lastName.trim().length() > 0
                && phone != null && phone.trim().length() > 0) {

            String passwordEncrypt = cryptWithMD5(password);

            Customer customer = new Customer();
            customer.setEmail(email);

            customer.setFirstname(firstName);
            customer.setLastname(lastName);
            customer.setPassword(passwordEncrypt);
            customer.setPhoneno(phone);

//----------เพิ่มให้เห็นว่า password อะไร จะได้ใช้ในการ test เข้าระบบ----- 
            customer.setRealpassword(password);
//-------------------------------------------------------------            

            CustomerJpaController customerJpaController = new CustomerJpaController(utx, emf);
            List<Customer> listCustomer = customerJpaController.findCustomerEntities();
            for (Customer c : listCustomer) {
                if (c.getEmail().equals(email)) {
                    System.out.println("Test");
                    request.setAttribute("message", "You cannot use this E-mail!");
                    getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
                    return;
                }
            }

            customerJpaController.create(customer);
            getServletContext().getRequestDispatcher("/index.html").forward(request, response);
            return;

        }

        System.out.println("Error");
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
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