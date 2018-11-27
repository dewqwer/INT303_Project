package jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.model.Address;
import jpa.model.Orders;
import jpa.model.Productreview;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-23T11:28:28")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> firstname;
    public static volatile SingularAttribute<Customer, String> password;
    public static volatile ListAttribute<Customer, Address> addressList;
    public static volatile SingularAttribute<Customer, Long> customerid;
    public static volatile SingularAttribute<Customer, String> realpassword;
    public static volatile ListAttribute<Customer, Productreview> productreviewList;
    public static volatile SingularAttribute<Customer, String> phoneno;
    public static volatile SingularAttribute<Customer, String> email;
    public static volatile ListAttribute<Customer, Orders> ordersList;
    public static volatile SingularAttribute<Customer, String> lastname;

}