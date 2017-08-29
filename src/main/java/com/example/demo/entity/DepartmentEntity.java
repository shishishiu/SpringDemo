package com.example.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "mst_department")
@Table(name = "mst_department")
public class DepartmentEntity {

	@OneToMany(mappedBy="departmentEntity")
	private List<MemberEntity> userList;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false)
    private int enable;

    public int getId()
   {
       return this.id;
   }

    public String getName()
   {
       return this.name;
   }

    public int getEnable()
   {
       return this.enable;
   }

    public List<MemberEntity> getUserList() {
    	return this.userList;
	}

}
