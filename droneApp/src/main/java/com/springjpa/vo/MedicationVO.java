package com.springjpa.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class MedicationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8917981941642127398L;

	private Long id;

	private String name;

	private String code;

	private Integer weight;

	private String image;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public Integer getWeight() {
		return weight;
	}


	public void setWeight(Integer weight) {
		this.weight = weight;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	@Override
	public String toString() {
		return "MedicationVO [name=" + name + ", code=" + code + ", weight=" + weight + ", image="
				+ image + "]";
	}


	public MedicationVO(Long id, String name, String code, Integer weight, String image) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.weight = weight;
		this.image = image;
	}


	public MedicationVO() {
	}

}
