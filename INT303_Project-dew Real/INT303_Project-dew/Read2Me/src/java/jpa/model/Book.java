
package jpa.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "BOOK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
    , @NamedQuery(name = "Book.findByIsbn", query = "SELECT b FROM Book b WHERE b.isbn = :isbn")
    , @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title")
    , @NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author")
    , @NamedQuery(name = "Book.findByDescription", query = "SELECT b FROM Book b WHERE b.description = :description")
    , @NamedQuery(name = "Book.findByPublisher", query = "SELECT b FROM Book b WHERE b.publisher = :publisher")
    , @NamedQuery(name = "Book.findByCategory", query = "SELECT b FROM Book b WHERE b.category = :category")
    , @NamedQuery(name = "Book.findByUnitprice", query = "SELECT b FROM Book b WHERE b.unitprice = :unitprice")
    , @NamedQuery(name = "Book.findBySalegroup", query = "SELECT b FROM Book b WHERE b.salegroup = :salegroup")})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ISBN")
    private String isbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "AUTHOR")
    private String author;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PUBLISHER")
    private String publisher;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CATEGORY")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UNITPRICE")
    private double unitprice;
    @Size(max = 100)
    @Column(name = "SALEGROUP")
    private String salegroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "isbn")
    private List<Review> reviewList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<Lineitem> lineitemList;

    public Book() {
    }

    public Book(String title, String author, String description, String publisher, String category, double unitprice) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.publisher = publisher;
        this.category = category;
        this.unitprice = unitprice;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public String getSalegroup() {
        return salegroup;
    }

    public void setSalegroup(String salegroup) {
        this.salegroup = salegroup;
    }

    @XmlTransient
    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @XmlTransient
    public List<Lineitem> getLineitemList() {
        return lineitemList;
    }

    public void setLineitemList(List<Lineitem> lineitemList) {
        this.lineitemList = lineitemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.model.Book[ isbn=" + isbn + " ]";
    }
    
}
