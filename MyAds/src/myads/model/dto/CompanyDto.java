package myads.model.dto;

public class CompanyDto {
	
	private int comid;
	private String comname;
	private String type;
	private String fax;
	private String email;
	private String adr;
	private int active;
	public int getComid() {
		return comid;
	}

	public void setComid(int comid) {
		this.comid = comid;
	}

	public String getComname() {
		return comname;
	}

	public void setComname(String comname) {
		this.comname = comname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public boolean valid;
	

	
	public boolean isValid() {
	       return valid;
	   }

	public void setValid(boolean newValid) {
	      valid = newValid;
	}

}
