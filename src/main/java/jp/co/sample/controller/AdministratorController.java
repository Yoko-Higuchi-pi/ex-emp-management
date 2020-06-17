package jp.co.sample.controller;

/**
 * 管理者　関連機能の処理の制御を行うコントローラ
 * 
 * @author yoko.higuchi
 *
 */
public class AdministratorController {
	private Integer id;
	private String name;
	private String mailAddress;
	private String password;
	
	public AdministratorController() {}
	
	public AdministratorController(Integer id, String name, String mailAddress, String password) {
		super();
		this.id = id;
		this.name = name;
		this.mailAddress = mailAddress;
		this.password = password;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "AdministratorController [id=" + id + ", name=" + name + ", mailAddress=" + mailAddress + ", password="
				+ password + "]";
	}
	
	
}
