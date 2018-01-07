package com.fsd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "s_user", catalog = "test")
public class SUser implements Serializable{
	private Integer id;
	private String name;
	private String email;
	private String password;
	private Date dob;
	private Set<SRole> SRoles = new HashSet<SRole>(0);
	public SUser() {
		
	}
	public SUser(SUser suser) {
		if(suser != null) {
			this.name = suser.getName();
			this.email = suser.getEmail();
			this.password = suser.getPassword();
			this.dob = suser.getDob();
			this.SRoles = suser.getSRoles();
		}
	}
//    public SUser(String name, String email, String password, Date dob, Set<SRole> SRoles) {
//
//        this.name = name;
//
//        this.email = email;
//
//        this.password = password;
//
//        this.dob = dob;
//
//        this.SRoles = SRoles;
//
//     }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", unique=true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "name" , length = 40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="email", length = 40)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="password", length = 200)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "SUser")
	@OneToMany()
	@JoinColumn(name="role_id")
	public Set<SRole> getSRoles() {
		return SRoles;
	}
	
	public void setSRoles(Set<SRole> sRoles) {
		SRoles = sRoles;
	}

}
