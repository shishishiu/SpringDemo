package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity(name = "mst_user")
@Table(name = "mst_user")
public class MemberEntity implements UserDetails
{
	@ManyToOne
	@JoinColumn(name = "department_id")
	private DepartmentEntity departmentEntity;
	 

    private static final long serialVersionUID = 1667698003975566301L;

    public enum Authority {ROLE_USER, ROLE_ADMIN}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false, unique = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String mailAddress;

    @Column(nullable = false)
    private int enabled=1;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public int getId()
   {
       return this.id;
   }

    public String getLoginId()
   {
       return this.loginId;
   }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getMailAddress()
    {
        return this.mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public int getEnabled()
    {
        return this.enabled;
    }
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
    
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Set<Authority> authorities;

    public MemberEntity() {}

    public MemberEntity(int id, String password) {
        this.id = id;
        this.password = password;
        this.enabled = 1;
        this.authorities = EnumSet.of(Authority.ROLE_USER);
    }
    
    public boolean isAdmin() {
        return this.authorities.contains(Authority.ROLE_ADMIN);
    }

    public void setAdmin(boolean isAdmin) {
        if (isAdmin) {
            this.authorities.add(Authority.ROLE_ADMIN);
        } else {
            this.authorities.remove(Authority.ROLE_ADMIN);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Authority authority : this.authorities) {
            authorities.add(new SimpleGrantedAuthority(authority.toString()));
        }
        return authorities;
    }

    public DepartmentEntity getDepartment() {
    	return this.departmentEntity;
	}
    	 
	public void setDepartment(DepartmentEntity departmentEntity) {
		this.departmentEntity = departmentEntity;
	}

}