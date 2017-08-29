package com.example.demo.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AutentificaForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  @NotNull
	  private String loginId;

	  @NotNull
	  private String password;
	  
		public String getLoginId(){
			return this.loginId;
		}
		public void setLoginId(String loginId){
			this.loginId = loginId;
		}
		public String getPassWord(){
			return this.password;
		}
		public void setPassWord(String password){
			this.password = password;
		}


}
