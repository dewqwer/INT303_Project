package jpa.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.model.Address;
import jpa.model.Orders;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-23T11:28:28")
@StaticMetamodel(Shipping.class)
public class Shipping_ { 

    public static volatile SingularAttribute<Shipping, Long> shippingid;
    public static volatile SingularAttribute<Shipping, Orders> orderid;
    public static volatile SingularAttribute<Shipping, Date> shippingdate;
    public static volatile SingularAttribute<Shipping, Double> shippingcost;
    public static volatile SingularAttribute<Shipping, String> status;
    public static volatile SingularAttribute<Shipping, Address> addressid;

}