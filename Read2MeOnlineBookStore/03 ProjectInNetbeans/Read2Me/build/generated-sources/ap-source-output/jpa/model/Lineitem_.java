package jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.model.Book;
import jpa.model.LineitemPK;
import jpa.model.Orders;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-23T11:28:28")
@StaticMetamodel(Lineitem.class)
public class Lineitem_ { 

    public static volatile SingularAttribute<Lineitem, LineitemPK> lineitemPK;
    public static volatile SingularAttribute<Lineitem, Integer> quantity;
    public static volatile SingularAttribute<Lineitem, Book> book;
    public static volatile SingularAttribute<Lineitem, Orders> orders;
    public static volatile SingularAttribute<Lineitem, Double> unitprice;

}