package com.springjpa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.constant.StateConstant;
import com.springjpa.constant.StatusConstant;
import com.springjpa.model.Drone;
import com.springjpa.model.Packet;
import com.springjpa.repository.PacketRepository;
import com.springjpa.vo.PacketVO;

@Service
@Transactional
public class PacketService {
	
	@Autowired 
	PacketRepository packageRepository;
	
	@Autowired 
	DroneService droneService;
	
	@Autowired 
	MedicationService medicationService;
	
	public List<Packet> listPacket(){
		List<Packet> packageList =  (List<Packet>) packageRepository.findAll();
		return packageList;
	}
	
	public Optional<Packet> findPacketById(long id){
		Optional<Packet> droneList =   packageRepository.findById(id);
		return droneList;
	}
	
	
	public void savePacket(PacketVO packetVO){
		
		Packet newPacket = new Packet();
		newPacket.setPacketCode(packetVO.getPacketCode());
		newPacket.setPacketName(packetVO.getPacketName());
		newPacket.setStatus(
				packetVO.getStatus() != null ? StatusConstant.getLabelFromCode(Integer.parseInt(packetVO.getStatus()))
						: StatusConstant.PROGRESS.getLabelKey());
		newPacket.setDrone(packetVO.getDrone());
		newPacket.getDrone().setState(StateConstant.LOADING.getLabelKey());
		newPacket.setMedication(packetVO.getMedication());
		packageRepository.save(newPacket);
	}
	
	public List<Packet> findBypacketCode(String packetCode){
		return packageRepository.findByPacketCode(packetCode);
	}
	
	public void deleteById(long id){
		packageRepository.deleteById(id);
	}
	
	public void updatePacket(String packetCode,PacketVO packet){
		List <Packet> editPacket = findBypacketCode(packetCode);
		editPacket.forEach(obj->{
			obj.setPacketCode(packet.getPacketCode());
			obj.setPacketName(packet.getPacketName());
			obj.setDrone(null);
			obj.setStatus(packet.getStatus());
			packageRepository.save(obj);
			
		});
		
	}
	
	public void updateStatusStatePacket(PacketVO packet){
		List <Packet> editPacket = findBypacketCode(packet.getPacketCode());
		editPacket.forEach(obj->{
			obj.getDrone().setState(StateConstant.getLabelFromCode(packet.getState()));
			obj.setStatus(StatusConstant.getLabelFromCode(Integer.parseInt(packet.getStatus())));
			packageRepository.save(obj);		
		});
		
	}
	
	public void updateStatusDelivaryPacket(Packet packet){
		Optional<Packet> editPacket = findPacketById(packet.getId());
		Packet updatePacket = editPacket.get();
		packageRepository.save(updatePacket);
	}
	
	public Boolean isRecordFull(){
		List<Packet>listDrone = (List<Packet>) packageRepository.findAll();
		if(listDrone.size() >= 10){
			return true;
		}
		return false;
	}
	
}
