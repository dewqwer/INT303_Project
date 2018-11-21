/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Dew2018
 */
@Embeddable
public class LineitemPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDERID")
    private long orderid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ISBN")
    private String isbn;

    public LineitemPK() {
    }

    public LineitemPK(long orderid, String isbn) {
        this.orderid = orderid;
        this.isbn = isbn;
    }

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orderid;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineitemPK)) {
            return false;
        }
        LineitemPK other = (LineitemPK) object;
        if (this.orderid != other.orderid) {
            return false;
        }
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.model.LineitemPK[ orderid=" + orderid + ", isbn=" + isbn + " ]";
    }
    
}
