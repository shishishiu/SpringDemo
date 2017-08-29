package com.example.demo.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.example.demo.customValidation.LoginIdUnique;

public class AddUsuarioForm {

	@NotNull
    @Size(min = 1, max = 255, message="{err.msg.size}")
	@LoginIdUnique(message="{err.msg.loginid.unique}")
    private String loginId;

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
	    
	    
}
