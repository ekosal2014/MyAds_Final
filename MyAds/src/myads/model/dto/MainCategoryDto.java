package myads.model.dto;

public class MainCategoryDto {
	
	private int id;
	//store encryption id
	private String id_security;
	private String ico_cls_name;
	private String name;
	private String dsc;
	private int active;
	public boolean valid;
	private SubCategoryDto subcategory;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDsc() {
		return dsc;
	}
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	public boolean isValid() {
	       return valid;
	   }

	public void setValid(boolean newValid) {
	      valid = newValid;
	}
	public String getIco_cls_name() {
		return ico_cls_name;
	}
	public void setIco_cls_name(String ico_cls_name) {
		this.ico_cls_name = ico_cls_name;
	}
	public SubCategoryDto getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(SubCategoryDto subcategory) {
		this.subcategory = subcategory;
	}
	public String getId_security() {
		return id_security;
	}
	public void setId_security(String id_security) {
		this.id_security = id_security;
	}

}
