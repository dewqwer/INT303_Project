package jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.model.Customer;
import jpa.model.Shipping;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-27T17:29:03")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile ListAttribute<Address, Shipping> shippingList;
    public static volatile SingularAttribute<Address, String> province;
    public static volatile SingularAttribute<Address, String> addressno;
    public static volatile SingularAttribute<Address, String> street;
    public static volatile SingularAttribute<Address, String> subdistrict;
    public static volatile SingularAttribute<Address, String> district;
    public static volatile SingularAttribute<Address, String> postcode;
    public static volatile SingularAttribute<Address, Customer> customerid;
    public static volatile SingularAttribute<Address, String> alley;
    public static volatile SingularAttribute<Address, Long> addressid;

}