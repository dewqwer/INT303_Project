package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jpa.model.Book;
import jpa.model.Lineitem;

public class ShoppingCart implements Serializable {

    private Map<String, Lineitem> cart;

    public ShoppingCart() {
        cart = new HashMap();
    }

    public void add(Book b) {
        Lineitem line = cart.get(b.getIsbn());

        //สร้างเงื่อนไข check ว่าเคยมีของอยู่หรือเปล่า 
        //-ถ้าไม่มีให้ทำการเพิ่มสินค้าเข้าไป แต่ถ้ามีอยู่แล้วให้ทำการเพิ่มจำนวนของสินค้านั้น      
        if (line == null) {
            cart.put(b.getIsbn(), new Lineitem(b));
        } else {
            line.setQuantity(line.getQuantity() + 1);
        }
    }

    public void updateCart(String isbn, int quantity){
        Lineitem line = cart.get(isbn);
        
        if(line != null){
            line.setQuantity(quantity);
            cart.put(isbn, line);
        }
        
    }
    
    public void remove(String isbn){
        cart.remove(isbn);
    }
    
    public double getTotalPrice(){
        double sum=0;
        Collection<Lineitem> lineItems = cart.values();
        for(Lineitem lineItem:lineItems){
            sum+=lineItem.getTotalLinePrice();
        }
        return sum;
    }
    
    public int getTotalQuantity(){
        int sum=0;
        Collection<Lineitem> lineItems = cart.values();
        for(Lineitem lineItem:lineItems){
            sum+=lineItem.getQuantity();
        }
        return sum;
    }
    
    public List<Lineitem> getLineItems() {
        return new ArrayList(cart.values());
    }

}
