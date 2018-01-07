package com.fsd.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "s_role", catalog="test")
public class SRole implements Serializable{
	private Integer id;
	private SUser suser;
	private String name;

	public SRole() {
		
	}
	
	public SRole(SUser suser) {
		this.suser = suser;
	}
	
	public SRole(SUser suser, String name) {
		this.suser = suser;
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="role_id", nullable =false)
	public SUser getSuser() {
		return suser;
	}

	public void setSuser(SUser suser) {
		this.suser = suser;
	}

}
