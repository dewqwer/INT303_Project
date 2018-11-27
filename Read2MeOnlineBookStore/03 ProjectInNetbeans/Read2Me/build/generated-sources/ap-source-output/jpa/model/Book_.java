package jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.model.Lineitem;
import jpa.model.Productreview;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-23T11:28:28")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, String> salegroup;
    public static volatile SingularAttribute<Book, String> author;
    public static volatile SingularAttribute<Book, String> isbn;
    public static volatile SingularAttribute<Book, String> description;
    public static volatile SingularAttribute<Book, String> publisher;
    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, String> category;
    public static volatile ListAttribute<Book, Productreview> productreviewList;
    public static volatile ListAttribute<Book, Lineitem> lineitemList;
    public static volatile SingularAttribute<Book, Double> unitprice;

}