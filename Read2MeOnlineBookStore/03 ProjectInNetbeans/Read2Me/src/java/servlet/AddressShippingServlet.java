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
import javax.transaction.UserTransaction;
import jpa.controller.AddressJpaController;
import jpa.model.Address;
import jpa.model.Customer;

/**
 *
 * @author Dew2018
 */
public class AddressShippingServlet extends HttpServlet {
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
            throws ServletException, IOException, Exception {
//ที่อยู่จัดส่งสินค้า
        String addressNo = request.getParameter("addressNo");
        String street = request.getParameter("street");
        String alley = request.getParameter("alley");
        String subdistrict = request.getParameter("subdistrict");
        String district = request.getParameter("district");
        String province = request.getParameter("province");
        String postcode = request.getParameter("postcode");
    
            if (addressNo != null && addressNo.trim().length() > 0
                && street != null && street.trim().length() > 0
                && alley != null && alley.trim().length() > 0
                && subdistrict != null && subdistrict.trim().length() > 0
                && district != null && district.trim().length() > 0
                && province != null && province.trim().length() > 0
                && postcode != null && postcode.trim().length() > 0) {

            Customer customer=(Customer) request.getAttribute("user");
            
            Address address = new Address();
            address.setAddressno(addressNo);
            address.setAlley(alley);
            address.setDistrict(district);
            address.setPostcode(postcode);
            address.setProvince(province);
            address.setStreet(street);
            address.setSubdistrict(subdistrict);
            address.setCustomerid(customer);

            AddressJpaController addressJpaController = new AddressJpaController(utx, emf);
            addressJpaController.create(address);
            getServletContext().getRequestDispatcher("/Payment").forward(request, response);
            return;
        }
            
            getServletContext().getRequestDispatcher("/addressShipping.jsp").forward(request, response);

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
            Logger.getLogger(AddressShippingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddressShippingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
