package myads.model.dto;

public class ProvinceDto {
	
	private int id;
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

}
