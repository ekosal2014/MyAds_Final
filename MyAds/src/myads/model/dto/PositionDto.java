package myads.model.dto;

public class PositionDto {
	private int PostId;
	private int RoleId;
	private String RoleName;
	private String PostAS;
	private String Dsc;
	private int active;
	
	public int getPostId() {
		return PostId;
	}
	public void setPostId(int postId) {
		PostId = postId;
	}
	public int getRoleId() {
		return RoleId;
	}
	public void setRoleId(int roleId) {
		RoleId = roleId;
	}
	public String getPostAS() {
		return PostAS;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public void setPostAS(String postAS) {
		PostAS = postAS;
	}
	public String getDsc() {
		return Dsc;
	}
	public void setDsc(String dsc) {
		Dsc = dsc;
	}
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}

}
