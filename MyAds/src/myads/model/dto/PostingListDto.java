
package myads.model.dto;

public class PostingListDto {
	private int PostingId;
	private int MemId;
	private String ProductName;
	private int Price;
	private String Phone;
	private String Adr;
	private String dsc;
	private String img;
	private String SubCateName;
	private String discount;
	
	public int getPostingId() {
		return PostingId;
	}
	public void setPostingId(int postingId) {
		PostingId = postingId;
	}
	public int getMemId() {
		return MemId;
	}
	public void setMemId(int memId) {
		MemId = memId;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getAdr() {
		return Adr;
	}
	public void setAdr(String adr) {
		Adr = adr;
	}
	public String getDsc() {
		return dsc;
	}
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSubCateName() {
		return SubCateName;
	}
	public void setSubCateName(String subCateName) {
		SubCateName = subCateName;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	

}
