package cn.web.news.entity;
/*
 * 用户 实体类
 * @author
 */
public class User { 
	
	private int uid;
	private String uname;
	private String upwd;
	private String age;
	public int getUid() {
		return uid;
	}
	public User() {
		
	}
	public User(String uname, String upwd) {
		super();
		this.uname = uname;
		this.upwd = upwd;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getUname() {
		return uname;
	}

	public String getUpwd() {
		return upwd;
	}
}
