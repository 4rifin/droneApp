package com.springjpa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.constant.StateConstant;
import com.springjpa.model.Drone;
import com.springjpa.repository.DroneRepository;
import com.springjpa.rest.controller.DroneRestController;
import com.springjpa.vo.DroneVO;

@Service
@Transactional
public class DroneService {
	
	@Autowired 
	DroneRepository droneRepository;
	
	public List<Drone> listDrone(){
		List<Drone> droneList =  (List<Drone>) droneRepository.findAll();
		return droneList;
	}
	
	public Optional<Drone> findDroneById(long id){
		Optional<Drone> droneList =   droneRepository.findById(id);
		return droneList;
	}
	
	
	public void saveDrone(DroneVO drone){
		
		Drone newDrone = new Drone();
		newDrone.setModel(drone.getModel());
		newDrone.setSerialNumber(drone.getSerialNumber());
		newDrone.setWeight(drone.getWeight());
		newDrone.setState(StateConstant.getLabelFromCode(drone.getStateCode()));
		newDrone.setBatteryCapacity(drone.getBatteryCapacity());
		
		droneRepository.save(newDrone);
	}
	
	public Drone findBySerialNumber(String serialNumber){
		return droneRepository.findBySerialNumber(serialNumber);
	}
	
	public void deleteById(long id){
		droneRepository.deleteById(id);
	}
	
	public void updateDrone(String serialNumber,DroneVO drone){
		Drone editDrone = findBySerialNumber(serialNumber);
		editDrone.setModel(drone.getModel());
		editDrone.setSerialNumber(drone.getModel());
		editDrone.setWeight(drone.getWeight());
		editDrone.setState(drone.getState());
		editDrone.setBatteryCapacity(drone.getBatteryCapacity());
		droneRepository.save(editDrone);
	}
	public Boolean isRecordFull(){
		List<Drone>listDrone = (List<Drone>) droneRepository.findAll();
		if(listDrone.size() >= 10){
			return true;
		}
		return false;
	}
	
	public Boolean existsBySerialNumber(String serialNumber){
		return droneRepository.existsBySerialNumber(serialNumber);
	}
	
	public Drone findByModel(String model){
		return droneRepository.findByModel(model);
	}
	
	public List <Drone> findBystate(String state){
		return droneRepository.findByState(state);
	}
	
	public List <Drone> findByBatteryCapacity(int levelA,int levelB){
		return droneRepository.findByBatteryCapacityBetween(levelA, levelB);
	}
}
