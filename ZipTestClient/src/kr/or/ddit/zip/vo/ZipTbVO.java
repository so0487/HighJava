package kr.or.ddit.zip.vo;

public class ZipTbVO {
	private String zipcode;
	private String sido;
	private String gugun;
	private String dong;
	private String ri;
	private String bldg;
	private String bunji;
	private int seq;
	
	
	
	
	//생성자
	public ZipTbVO(String zipcode, String sido, String gugun, String dong, String ri, String bldg, String bunji,
			int seq) {
		super();
		this.zipcode = zipcode;
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
		this.ri = ri;
		this.bldg = bldg;
		this.bunji = bunji;
		this.seq = seq;
	}




	public String getZipcode() {
		return zipcode;
	}




	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}




	public String getSido() {
		return sido;
	}




	public void setSido(String sido) {
		this.sido = sido;
	}




	public String getGugun() {
		return gugun;
	}




	public void setGugun(String gugun) {
		this.gugun = gugun;
	}




	public String getDong() {
		return dong;
	}




	public void setDong(String dong) {
		this.dong = dong;
	}




	public String getRi() {
		return ri;
	}




	public void setRi(String ri) {
		this.ri = ri;
	}




	public String getBldg() {
		return bldg;
	}




	public void setBldg(String bldg) {
		this.bldg = bldg;
	}




	public String getBunji() {
		return bunji;
	}




	public void setBunji(String bunji) {
		this.bunji = bunji;
	}




	public int getSeq() {
		return seq;
	}




	public void setSeq(int seq) {
		this.seq = seq;
	}



	
	//getter&setter
	
	
	
}
