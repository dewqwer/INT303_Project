/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.print.Collation;
import jpa.model.Book;
import jpa.model.Lineitem;

/**
 *
 * @author INT303
 */
public class ShoppingCart implements Serializable{
    private Map<String,Lineitem> cart; //ใช้ map ในการเก็บข้อมูลง่ายกว่า เพราะใช้ key ในการหาข้อมูล
    
    public ShoppingCart(){
        cart=new HashMap();
    }
    
    public void add(Book b){
        Lineitem line=cart.get(b.getIsbn());
    
    //สร้างเงื่อนไข check ว่าเคยมีของอยู่หรือเปล่า 
    //-ถ้าไม่มีจะเป็น null ให้ทำการเพิ่มสินค้าเข้าไป แต่ถ้ามีอยู่แล้วให้ทำการเพิ่มจำนวนของสินค้านั้น      
        if(line == null){
            cart.put(b.getIsbn(),new Lineitem(b));
        }else{
            line.setQuantity(line.getQuantity()+1);
        }
    }
    
    public void remove(Book b){
        cart.remove(""+b.getIsbn());
    }

    public void remove(String productCode){
        cart.remove(productCode);
    }
    
    public float getTotalPrice(){
        float sum=0;
        Collection<Lineitem> lineItems = cart.values();
        for(Lineitem lineItem:lineItems){
            sum+=lineItem.getTotalPrice();
        }
        return sum;
    }
    
    public int getTotalQuantiry(){
        int sum=0;
        Collection<Lineitem> lineItems = cart.values();
        for(Lineitem lineItem:lineItems){
            sum+=lineItem.getQuantity();
        }
        return sum;
    }
    
    public List<Lineitem> getLineItems(){
        return new ArrayList(cart.values());
    }
    
    
}
