package com.j3a.assurance.managedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.objetService.ObjectService;

@Component
public class ManagedIndexConnex  {
	
	@Autowired
	ObjectService objectService;
	
	
	String login;
	String password;
	Boolean askMdpForgot=false;
	
	
	
	public void logIn(){
		
	}
	
	
	public ObjectService getObjectService() {
		return objectService;
	}
	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
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
	public Boolean getAskMdpForgot() {
		return askMdpForgot;
	}
	public void setAskMdpForgot(Boolean askMdpForgot) {
		this.askMdpForgot = askMdpForgot;
	}

	
}
