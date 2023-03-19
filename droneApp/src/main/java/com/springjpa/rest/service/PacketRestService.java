package com.springjpa.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springjpa.constant.StatusConstant;
import com.springjpa.model.Drone;
import com.springjpa.model.Medication;
import com.springjpa.model.Packet;
import com.springjpa.repository.PacketRepository;
import com.springjpa.rest.model.MessageInfo;
import com.springjpa.service.DroneService;
import com.springjpa.service.MedicationService;
import com.springjpa.service.PacketService;
import com.springjpa.vo.PacketVO;

@Service
@Transactional
public class PacketRestService {
	
	@Autowired 
	PacketRepository packetRepository;
	@Autowired 
	PacketService packetService;
	
	@Autowired 
	DroneService droneService;

	@Autowired 
	MedicationService medicationService;
	
	public ResponseEntity<MessageInfo> packetGetAll(MessageInfo messageInfo) {

		List<Packet> packetList = packetService.listPacket();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		if (packetList.isEmpty()) {
			messageInfo.setCode(HttpStatus.BAD_REQUEST.toString());
			messageInfo.setStatus(HttpStatus.BAD_REQUEST);
			messageInfo.setMessage("List Packet Is Empty");
			messageInfo.setResult(packetList);
			return new ResponseEntity<>(messageInfo, HttpStatus.BAD_REQUEST);
		}

		messageInfo.setCode(HttpStatus.OK.toString());
		messageInfo.setStatus(HttpStatus.OK);
		messageInfo.setMessage("Success");
		messageInfo.setResult(packetList);
		return new ResponseEntity<>(messageInfo, HttpStatus.OK);
	}

	public ResponseEntity<String> packetGetId(long id) {
		
		Optional<Packet> packetById = packetService.findPacketById(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		ResponseEntity<String> responseEntity = null;

		String body = "";
		Gson gson = new Gson();
		if (packetById == null) {
			body = "null";
		} else {
			body = gson.toJson(packetById);
		}

		if (packetById == null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.NOT_FOUND);
		}

		if (packetById != null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.OK);
		}

		return responseEntity;
	}

	public ResponseEntity<String> packetGetBypacketCode(String packetCode) {

		List <Packet> packetByPacketCode = packetService.findBypacketCode(packetCode);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		ResponseEntity<String> responseEntity = null;

		String body = "";
		Gson gson = new Gson();
		if (packetByPacketCode.isEmpty()) {
			body = "null";
		} else {
			body = gson.toJson(packetByPacketCode.get(0));
			
		}

		if (packetByPacketCode == null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.NOT_FOUND);
		}

		if (packetByPacketCode != null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.OK);
		}

		return responseEntity;
	}
	
	public ResponseEntity<MessageInfo> createPacket(PacketVO packetVO,MessageInfo messageInfo) {
		List <Packet> packetList = packetService.findBypacketCode(packetVO.getPacketCode());
		packetList = packetList.stream().filter(x -> x.getStatus().contains(StatusConstant.PROGRESS.getLabelKey()))
				.collect(Collectors.toList());
		if (!packetList.isEmpty()) {
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Drone on Duty");
			return new ResponseEntity<>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}
		
		Drone drone = droneService.findBySerialNumber(packetVO.getDroneSerialNumber());
		if (drone == null) {
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Drone not Found");
			return new ResponseEntity<>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}else {
			packetVO.setDrone(drone);
		}
		
		Medication medication = medicationService.findMedicationByCode(packetVO.getMedicationCode());
		if (medication == null) {
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Medication Not Found");
			return new ResponseEntity<>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}else {
			packetVO.setMedication(medication);
		}
		
		// Prevent the drone from being in LOADING state if the battery level is below 25%;
		if (drone.getBatteryCapacity() < 25) {
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("battery level is below 25%");
			return new ResponseEntity<>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}
		
		//Prevent the drone from being loaded with more weight that it can carry
		if (medication.getWeight() > drone.getWeight()) {
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Medication "  + medication.getName() + " more a heavy than droone with serialNumber "
					+ drone.getSerialNumber());
			return new ResponseEntity<>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}
		
		packetService.savePacket(packetVO);
		
		PacketVO newPacketVO = new PacketVO();
		//Packet drone = droneService.findBySerialNumber(droneVO.getSerialNumber());
		BeanUtils.copyProperties(packetVO, newPacketVO);
		newPacketVO.setStatus(StatusConstant.getLabelFromCode(Integer.parseInt(packetVO.getStatus())));
		
		messageInfo.setCode(HttpStatus.CREATED.toString());
		messageInfo.setStatus(HttpStatus.CREATED);
		messageInfo.setMessage("Success");
		messageInfo.setResult(newPacketVO.toString());
		return new ResponseEntity<>(messageInfo, HttpStatus.CREATED);
	}

	public ResponseEntity<MessageInfo> updatePacket(PacketVO packetVO,MessageInfo messageInfo) {
		
		List <Packet> packetList = packetService.findBypacketCode(packetVO.getPacketCode());

		if (packetList == null) {
			System.out.println("Packet with code " + packetVO.getPacketCode() + " not found");
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Packet with model " + packetVO.getPacketCode() + " not found");
			messageInfo.setResult(packetVO.toString());
			return new ResponseEntity<MessageInfo>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}
		
		packetService.updatePacket(packetVO.getPacketCode(), packetVO);
		messageInfo.setCode(HttpStatus.OK.toString());
		messageInfo.setStatus(HttpStatus.OK);
		messageInfo.setMessage("Packet with Model " + packetVO.getPacketCode()+ " Success update");
		messageInfo.setResult(packetVO);

		return new ResponseEntity<MessageInfo>(messageInfo, HttpStatus.OK);
	}
	
	public ResponseEntity<MessageInfo> updateStatusStatePacket(PacketVO packetVO, MessageInfo messageInfo) {

		List <Packet> packetList = packetService.findBypacketCode(packetVO.getPacketCode());

		if (packetList == null) {
			System.out.println("Packet with code " + packetVO.getPacketCode() + " not found");
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Packet with model " + packetVO.getPacketCode() + " not found");
			messageInfo.setResult(packetVO.toString());
			return new ResponseEntity<MessageInfo>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}

		packetService.updatePacket(packetVO.getPacketCode(), packetVO);
		messageInfo.setCode(HttpStatus.OK.toString());
		messageInfo.setStatus(HttpStatus.OK);
		messageInfo.setMessage("Packet with Model " + packetVO.getPacketCode() + " Success update");
		messageInfo.setResult(packetVO.toString());

		return new ResponseEntity<MessageInfo>(messageInfo, HttpStatus.OK);
	}

	public ResponseEntity<MessageInfo> deletePacket(long id, @Valid MessageInfo messageInfo) {

		Optional<Packet> packet = packetService.findPacketById(id);
		if (packet == null) {
			System.out.println("disable to delete. Packet with " + packet.get().getPacketName() + " not found");
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Unable to delete. Packet with name " + packet.get().getPacketName() + " not found");
			messageInfo.setResult(packet);
			return new ResponseEntity<MessageInfo>(HttpStatus.ALREADY_REPORTED);
		}
		PacketVO droneVO = new PacketVO();
		BeanUtils.copyProperties(packet, droneVO);
		packetService.deleteById(id);
		messageInfo.setCode(HttpStatus.OK.toString());
		messageInfo.setStatus(HttpStatus.OK);
		messageInfo.setMessage("Packet with packet " + packet.get().getPacketName() + " Success delete");
		messageInfo.setResult(droneVO);
		return new ResponseEntity<MessageInfo>(messageInfo, HttpStatus.OK);
	}
}
