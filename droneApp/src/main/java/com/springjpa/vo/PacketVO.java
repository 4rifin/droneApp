package com.springjpa.vo;

import java.io.Serializable;

import com.springjpa.model.Drone;
import com.springjpa.model.Medication;

public class PacketVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8917981941642127398L;

	private Long id;

	private String packetName;

	private String packetCode;

	private String status;
	
	private Integer state;

	private Drone drone;
	
	private String droneSerialNumber;

	private Medication medication;

	private String medicationCode;
	
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

	
	public String getDroneSerialNumber() {
		return droneSerialNumber;
	}

	public void setDroneSerialNumber(String droneSerialNumber) {
		this.droneSerialNumber = droneSerialNumber;
	}

	public String getMedicationCode() {
		return medicationCode;
	}

	public void setMedicationCode(String medicationCode) {
		this.medicationCode = medicationCode;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public PacketVO(String packetName, String packetCode, String status, Drone drone, String droneSerialNumber,
			Medication medication, String medicationCode) {
		super();
		this.packetName = packetName;
		this.packetCode = packetCode;
		this.status = status;
		this.drone = drone;
		this.droneSerialNumber = droneSerialNumber;
		this.medication = medication;
		this.medicationCode = medicationCode;
	}

	@Override
	public String toString() {
		return "PacketVO [packetName=" + packetName + ", packetCode=" + packetCode + ", status=" + status + ", drone="
				+ drone + ", droneSerialNumber=" + droneSerialNumber + ", medication=" + medication + ", medicationCode=" + medicationCode
				+ "]";
	}

	public PacketVO() {
		super();
	}


}
