package com.example.demo.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.thymeleaf.util.StringUtils;

import com.example.demo.customValidation.LoginIdUnique;

@LoginIdUnique(message="{err.msg.loginid.unique}")
public class AddUsuarioForm {

	@NotNull
    @Size(min = 1, max = 255, message="{err.msg.size}")
    private String loginId;
	
    private int hidId;

	@NotNull
    @Size(min = 1, max = 255)
    private String username;

	@NotNull
    @Size(min = 1, max = 255)
    private String password = "99999";

    @Size(min = 1, max = 255, message="{err.msg.size}")
    @Email
    private String mailAddress;

	@NotNull
    @Size(min = 1, max = 255, message="{err.msg.size}")
    private String departmentname;


    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public int getHidId() {
        return hidId;
    }
    public void setHidId(int hidId) {
        this.hidId = hidId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
	    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }
	    
//    /**
//     * getter para validation
//     */
//    @AssertTrue(message="")
//    public boolean isValidLoginId(){
//    	if(StringUtils.isEmpty(this.hidLoginId)){
//    		return true;
//    	}else{
//    		return true;
//    	}
//    }
}
