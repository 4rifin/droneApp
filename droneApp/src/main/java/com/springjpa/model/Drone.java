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
@Table(name = "master_drone")
public class Drone implements Serializable {

	private static final long serialVersionUID = 9297958987477799L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "serial_number")
	private String serialNumber;

	@Column(name = "model")
	private String model;

	@Column(name = "wight")
	private Integer weight;

	@Column(name = "baterry_capacity")
	private Integer batteryCapacity;

	@Column(name = "state")
	private String state;


	@OneToMany(targetEntity = Packet.class, mappedBy = "id", orphanRemoval = false, fetch = FetchType.LAZY)
	private Set<Packet> packet;

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public Integer getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(Integer batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Drone [id=" + id + ", serialNumber=" + serialNumber + ", model=" + model + ", weight=" + weight
				+ ", batteryCapacity=" + batteryCapacity + ", state=" + state + "]";
	}

	public Drone() {
	}

}
