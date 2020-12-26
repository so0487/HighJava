package kr.or.ddit.prod.vo;

public class LProdVo {
	private int lprodId;
	private String lprodGu;
	private String lprodName;
	
	
	
	
	public int getLprodId() {
		return lprodId;
	}
	public void setLprodId(int lprodId) {
		this.lprodId = lprodId;
	}
	public String getLprodGu() {
		return lprodGu;
	}
	public void setLprodGu(String lprodGu) {
		this.lprodGu = lprodGu;
	}
	public String getLprodName() {
		return lprodName;
	}
	public void setLprodNm(String lprodName) {
		this.lprodName = lprodName;
	}
	public LProdVo(int lprodId, String lprodGu, String lprodName) {
		super();
		this.lprodId = lprodId;
		this.lprodGu = lprodGu;
		this.lprodName = lprodName;
	}
	
	
	
	
	


}
