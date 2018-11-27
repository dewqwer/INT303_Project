package jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.model.Orders;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-23T11:28:28")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SingularAttribute<Payment, String> method;
    public static volatile SingularAttribute<Payment, Orders> orderid;
    public static volatile SingularAttribute<Payment, Long> paymentid;
    public static volatile SingularAttribute<Payment, Double> totalprice;

}