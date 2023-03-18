package com.springjpa.model;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "trans_packet")
public class Packet implements Serializable {

	private static final long serialVersionUID = 9297958987477799L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "packetName")
	private String packetName;

	@Column(name = "packetCode")
	private String packetCode;

	@Column(name = "status")
	private String status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "drone_id", nullable = true)
	@Fetch(FetchMode.JOIN)
	private Drone drone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medication_id", nullable = true)
	@Fetch(FetchMode.JOIN)
	private Medication medication;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPacketName() {
		return packetName;
	}


	public void setPacketName(String packetName) {
		this.packetName = packetName;
	}


	public String getPacketCode() {
		return packetCode;
	}


	public void setPacketCode(String packetCode) {
		this.packetCode = packetCode;
	}

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Drone getDrone() {
		return drone;
	}


	public void setDrone(Drone drone) {
		this.drone = drone;
	}


	public Medication getMedication() {
		return medication;
	}


	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	
	@Override
	public String toString() {
		return "Packet [packetName=" + packetName + ", packetCode=" + packetCode + ", status=" + status
				+ ", drone=" + drone + ", medication=" + medication + "]";
	}

	public Packet() {
		super();
	}

	

}
