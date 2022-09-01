
public class User {
	protected String name;
	protected String password;
	protected String email;
	protected String language;
	protected String Login_userid;
	protected String address;
	protected String reset;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLogin_userid() {
		return Login_userid;
	}
	public void setLogin_userid(String Login_userid) {
		this.Login_userid = Login_userid;
	}
	public String getaddress() {
		return address;
	}
	public void setaddress(String address) {
		this.address = address;
	}
	public String getreset() {
		return reset;
	}
	public void setreset(String reset) {
		this.reset = reset;
	}
	public User(String name, String password, String email, String language, String Login_userid, String address, String reset) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.language = language;
		this.Login_userid = Login_userid;
		this.address = address;
		this.reset = reset;
	}
}
