package servlet.account;

import java.io.IOException;
import java.util.ArrayList;
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
import jpa.controller.AddressJpaController;
import jpa.controller.CustomerJpaController;
import jpa.controller.exceptions.NonexistentEntityException;
import jpa.controller.exceptions.RollbackFailureException;
import jpa.model.Address;
import jpa.model.Customer;

public class AddAddressServlet extends HttpServlet {

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

            CustomerJpaController customerJpaCtrl = new CustomerJpaController(utx, emf);
            Customer customer = (Customer) request.getSession().getAttribute("user");

            AddressJpaController addressJpaCtrl = new AddressJpaController(utx, emf);
            Address address = new Address(addressNo, street, alley, subdistrict, district, province, postcode);

            if (customer != null) {
                address.setCustomerid(customer);

                List<Address> addressList = customer.getAddressList();
                if (addressList == null) {
                    addressList = new ArrayList<>();
                }
                addressList.add(address);

                try {
                    addressJpaCtrl.create(address);
                    customerJpaCtrl.edit(customer);

                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(AddAddressServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RollbackFailureException ex) {
                    Logger.getLogger(AddAddressServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(AddAddressServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.getSession().setAttribute("user", customer);
            }
            
            response.sendRedirect("Checkout");
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
