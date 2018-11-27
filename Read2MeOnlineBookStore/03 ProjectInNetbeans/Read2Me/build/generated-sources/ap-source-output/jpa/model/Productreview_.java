package jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.model.Book;
import jpa.model.Customer;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-27T17:29:03")
@StaticMetamodel(Productreview.class)
public class Productreview_ { 

    public static volatile SingularAttribute<Productreview, Book> isbn;
    public static volatile SingularAttribute<Productreview, Integer> rating;
    public static volatile SingularAttribute<Productreview, Customer> customerid;
    public static volatile SingularAttribute<Productreview, String> comment;
    public static volatile SingularAttribute<Productreview, Long> reviewid;

}