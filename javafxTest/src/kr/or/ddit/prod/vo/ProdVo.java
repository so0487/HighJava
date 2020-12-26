package kr.or.ddit.prod.vo;

public class ProdVo {
	private int prodId;
	private String prodName;
	private String prodLgu;
	private String prodBuyer;
	private String prodCost;
	private String prodPrice;
	private String prodSale;
	private String prodOutline;
	private String prodDetail;
	
	
	
	public ProdVo(int prodId, String prodName, String prodLgu, String prodBuyer, String prodCost, String prodPrice,
			String prodSale, String prodOutline, String prodDetail) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodLgu = prodLgu;
		this.prodBuyer = prodBuyer;
		this.prodCost = prodCost;
		this.prodPrice = prodPrice;
		this.prodSale = prodSale;
		this.prodOutline = prodOutline;
		this.prodDetail = prodDetail;
		
		
		
		
		
	}



	public int getProdId() {
		return prodId;
	}



	public void setProdId(int prodId) {
		this.prodId = prodId;
	}



	public String getProdName() {
		return prodName;
	}



	public void setProdName(String prodName) {
		this.prodName = prodName;
	}



	public String getProdLgu() {
		return prodLgu;
	}



	public void setProdLgu(String prodLgu) {
		this.prodLgu = prodLgu;
	}



	public String getProdBuyer() {
		return prodBuyer;
	}



	public void setProdBuyer(String prodBuyer) {
		this.prodBuyer = prodBuyer;
	}



	public String getProdCost() {
		return prodCost;
	}



	public void setProdCost(String prodCost) {
		this.prodCost = prodCost;
	}



	public String getProdPrice() {
		return prodPrice;
	}



	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}



	public String getProdSale() {
		return prodSale;
	}



	public void setProdSale(String prodSale) {
		this.prodSale = prodSale;
	}



	public String getProdOutline() {
		return prodOutline;
	}



	public void setProdOutline(String prodOutline) {
		this.prodOutline = prodOutline;
	}



	public String getProdDetail() {
		return prodDetail;
	}



	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	
	
	
	
}
