package com.dlaq.test.jdbc.mybatis.basic;

public class TUser {

	private String id_;
	private String username_;
	private String realName;
	
	public String getId() {
		return id_;
	}
	public void setId(String id) {
		this.id_ = id;
	}
	public String getUsername() {
		return username_;
	}
	public void setUsername(String username) {
		this.username_ = username;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@Override
	public String toString() {
		return "$TUser [id="+id_+",username="+username_+",realName="+realName+"]";
	}
	
}
