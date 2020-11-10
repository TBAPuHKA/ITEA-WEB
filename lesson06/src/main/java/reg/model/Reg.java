package reg.model;

public class Reg {
	
	private String login;
	private String password;
	private String rePassword;
	private String name;
	private String gender;
	private String address;
	private String comment;
	private String agree;
	
	public Reg() {
	}

	public Reg(String login, String password, String rePassword, String name, String gender, String address,
			String comment, String agree) {
		super();
		this.login = login;
		this.password = password;
		this.rePassword = rePassword;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.comment = comment;
		this.agree = agree;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAgree() {
		return agree;
	}

	public void setAgree(String agree) {
		this.agree = agree;
	}
	
	

}
