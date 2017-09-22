package myads.model.dto;

public class SubCategoryDto {
	
	private int id;
	private int subid;
	//store subid Encryption
	private String subid_security;
	private String Catename;
	private String class_name;
	private String name;
	private String dsc;
	private int active;
	public boolean valid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSubid() {
		return subid;
	}
	public void setSubid(int subid) {
		this.subid = subid;
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
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getCatename() {
		return Catename;
	}
	public void setCatename(String catename) {
		Catename = catename;
	}
	public String getSubid_security() {
		return subid_security;
	}
	public void setSubid_security(String subid_security) {
		this.subid_security = subid_security;
	}

}
