package jpa.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.model.Customer;
import jpa.model.Lineitem;
import jpa.model.Payment;
import jpa.model.Shipping;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-27T17:29:03")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Date> ordereddate;
    public static volatile SingularAttribute<Orders, String> orderstatus;
    public static volatile SingularAttribute<Orders, Shipping> shipping;
    public static volatile SingularAttribute<Orders, Long> orderid;
    public static volatile SingularAttribute<Orders, Customer> customerid;
    public static volatile SingularAttribute<Orders, Payment> payment;
    public static volatile ListAttribute<Orders, Lineitem> lineitemList;

}