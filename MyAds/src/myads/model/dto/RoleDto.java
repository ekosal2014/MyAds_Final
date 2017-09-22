package myads.model.dto;

public class RoleDto {
	private int RoleId;
	private String Role;
	private String Dsc;
	private int active;
	
	public int getRoleId() {
		return RoleId;
	}
	public void setRoleId(int roleId) {
		RoleId = roleId;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getDsc() {
		return Dsc;
	}
	public void setDsc(String dsc) {
		Dsc = dsc;
	}

}
