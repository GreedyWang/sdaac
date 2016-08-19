package sdaac.wym.app.entity.vave;
// default package



/**
 * AccountGoods entity. @author MyEclipse Persistence Tools
 */

public class AccountGoods  implements java.io.Serializable {


    // Fields    

     private Integer id;
//     private Integer goodsId;
//     private Integer accountId;
     private Integer quantity;
     
     private Goods goods = new Goods();
     private Account account = new Account();


    // Constructors

    /** default constructor */
    public AccountGoods() {
    }

    
    /** full constructor */
    public AccountGoods( Integer quantity) {
       // this.goodsId = goodsId;
     //   this.accountId = accountId;
        this.quantity = quantity;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }


    public Goods getGoods() {
		return goods;
	}


	public void setGoods(Goods goods) {
		this.goods = goods;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
   








}