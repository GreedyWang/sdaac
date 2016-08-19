package sdaac.wym.app.entity.vave;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private Integer price;
     private String img;
     private Integer quantity;
     private Set accounts = new HashSet(0);


    // Constructors

    /** default constructor */
    public Goods() {
    }
    
    public Goods(Integer pId) {
    	id=pId;
    }
    
	/** minimal constructor */
    public Goods(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
    
    /** full constructor */
    public Goods(String name, Integer price, String img, Integer quantity, Set accounts) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.quantity = quantity;
        this.accounts = accounts;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return this.price;
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImg() {
        return this.img;
    }
    
    public void setImg(String img) {
        this.img = img;
    }

    public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set getAccounts() {
        return this.accounts;
    }
    
    public void setAccounts(Set accounts) {
        this.accounts = accounts;
    }
   








}