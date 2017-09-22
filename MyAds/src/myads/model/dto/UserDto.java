package myads.model.dto;

public class UserDto {
	private int Id;
	private int postId;
	private String name;
	private String positionAs;
	private String sex;
	private String photo;
	private String dob;
	private String password;
	private String email;
	private String phone;
	private String address;
	private int active;
	
	public boolean valid;

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getPositionAs() {
		return positionAs;
	}
	public void setPositionAs(String positionAs) {
		this.positionAs = positionAs;
	}
}
