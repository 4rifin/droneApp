package com.springjpa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "master_medication")
public class Medication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6702272332654323195L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "wight")
	private Integer weight;

	@Column(name = "image")
	private String image;
//
//	@OneToMany(targetEntity = Packet.class, mappedBy = "id", orphanRemoval = false, fetch = FetchType.LAZY)
//	private Set<Packet> packet;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Medication(Long id, String name, String code, Integer weight, String image) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.weight = weight;
		this.image = image;
	}

	public Medication() {
	}

}
