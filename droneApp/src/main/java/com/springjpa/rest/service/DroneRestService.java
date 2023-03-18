package com.springjpa.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.springjpa.constant.StateConstant;
import com.springjpa.model.Drone;
import com.springjpa.repository.DroneRepository;
import com.springjpa.rest.model.MessageInfo;
import com.springjpa.service.DroneService;
import com.springjpa.vo.DroneVO;

@Service
@Transactional
public class DroneRestService {
	
	@Autowired 
	DroneRepository droneRepository;
	@Autowired 
	DroneService droneService;
	
	public ResponseEntity<MessageInfo> droneGetAll(MessageInfo messageInfo) {

		List<Drone> droneList = droneService.listDrone();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		if (droneList.isEmpty()) {
			messageInfo.setCode(HttpStatus.BAD_REQUEST.toString());
			messageInfo.setStatus(HttpStatus.BAD_REQUEST);
			messageInfo.setMessage("List Drone Is Empty");
			messageInfo.setResult(droneList);
			return new ResponseEntity<>(messageInfo, HttpStatus.BAD_REQUEST);
		}

		messageInfo.setCode(HttpStatus.OK.toString());
		messageInfo.setStatus(HttpStatus.OK);
		messageInfo.setMessage("Success");
		messageInfo.setResult(droneList);
		return new ResponseEntity<>(messageInfo, HttpStatus.OK);
	}

	public ResponseEntity<String> droneGetId(long id) {
		
		Optional<Drone> droneById = droneService.findDroneById(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		ResponseEntity<String> responseEntity = null;

		String body = "";
		Gson gson = new Gson();
		if (droneById == null) {
			body = "null";
		} else {
			body = gson.toJson(droneById);
		}

		if (droneById == null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.NOT_FOUND);
		}

		if (droneById != null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.OK);
		}

		return responseEntity;
	}

	public ResponseEntity<String> droneGetSerialNumber(String serialNumber) {

		Drone droneBySerialNumber = droneService.findBySerialNumber(serialNumber);
		DroneVO droneVO = new DroneVO();
		BeanUtils.copyProperties(droneBySerialNumber, droneVO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		ResponseEntity<String> responseEntity = null;

		String body = "";
		Gson gson = new Gson();
		if (droneBySerialNumber == null) {
			body = "null";
		} else {
			body = gson.toJson(droneVO);
		}

		if (droneBySerialNumber == null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.NOT_FOUND);
		}

		if (droneBySerialNumber != null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.OK);
		}

		return responseEntity;
	}
	
	public ResponseEntity<String> droneGetByState(String state) {

		List <Drone> droneList = droneService.findBystate(state);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		ResponseEntity<String> responseEntity = null;

		String body = "";
		Gson gson = new Gson();
		if (droneList.isEmpty()) {
			body = gson.toJson("Data Not Found");
		} else {
			body = gson.toJson(droneList);
		}

		if (droneList.isEmpty()) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.NOT_FOUND);
		}

		if (droneList != null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.OK);
		}

		return responseEntity;
	}
	
	public ResponseEntity<String> droneGetByBatteryCapacity(int levelA,int levelB) {

		List <Drone> droneList = droneService.findByBatteryCapacity(levelA, levelB);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		ResponseEntity<String> responseEntity = null;

		String body = "";
		Gson gson = new Gson();
		if (droneList.isEmpty()) {
			body = gson.toJson("Data Not Found");
		} else {
			body = gson.toJson(droneList);
		}

		if (droneList.isEmpty()) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.NOT_FOUND);
		}

		if (droneList != null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.OK);
		}

		return responseEntity;
	}


	public ResponseEntity<String> droneGetModel(String model) {

		Drone droneByModel = droneService.findByModel(model);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		ResponseEntity<String> responseEntity = null;

		String body = "";
		Gson gson = new Gson();
		if (droneByModel == null) {
			body = "null";
		} else {
			body = gson.toJson(droneByModel);
		}

		if (droneByModel == null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.NOT_FOUND);
		}

		if (droneByModel != null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.OK);
		}

		return responseEntity;
	}
	
	public ResponseEntity<MessageInfo> createDrone(DroneVO droneVO,MessageInfo messageInfo) {
		Drone gerDroneBySerialNumber = droneService.findBySerialNumber(droneVO.getSerialNumber());
		
		if (gerDroneBySerialNumber != null) {
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Duplicate serialNumber of drone");
			return new ResponseEntity<>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}
		
		if(droneVO.getWeight() > 500) {
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Weight Limit 500gr max");
			return new ResponseEntity<>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}
		
		if (droneService.isRecordFull()){
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Record drones Full");
			return new ResponseEntity<>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}
		droneService.saveDrone(droneVO);
		
		DroneVO newDroneVO = new DroneVO();
		//Drone drone = droneService.findBySerialNumber(droneVO.getSerialNumber());
		BeanUtils.copyProperties(droneVO, newDroneVO);
		newDroneVO.setState(StateConstant.getLabelFromCode(droneVO.getStateCode()));
		
		messageInfo.setCode(HttpStatus.CREATED.toString());
		messageInfo.setStatus(HttpStatus.CREATED);
		messageInfo.setMessage("Success");
		messageInfo.setResult(newDroneVO.toString());
		return new ResponseEntity<>(messageInfo, HttpStatus.CREATED);
	}

	public ResponseEntity<MessageInfo> updateDrone(DroneVO droneVO,MessageInfo messageInfo) {
		
		Drone drone = droneService.findByModel(droneVO.getModel());

		if (drone == null) {
			System.out.println("Drone with model " + droneVO.getModel() + " not found");
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Drone with model " + droneVO.getModel() + " not found");
			messageInfo.setResult(drone);
			return new ResponseEntity<MessageInfo>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}
		
		droneService.updateDrone(droneVO.getSerialNumber(), droneVO);
		messageInfo.setCode(HttpStatus.OK.toString());
		messageInfo.setStatus(HttpStatus.OK);
		messageInfo.setMessage("Drone with Model " + droneVO.getModel() + " Success update");
		messageInfo.setResult(droneVO);

		return new ResponseEntity<MessageInfo>(messageInfo, HttpStatus.OK);
	}

	public ResponseEntity<MessageInfo> deleteDrone(long id, @Valid MessageInfo messageInfo) {

		Optional<Drone> drone = droneService.findDroneById(id);
		if (drone == null) {
			System.out.println("disable to delete. Drone with model " + drone.get().getModel() + " not found");
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Unable to delete. Customer with name " + drone.get().getModel() + " not found");
			messageInfo.setResult(drone);
			return new ResponseEntity<MessageInfo>(HttpStatus.ALREADY_REPORTED);
		}
		DroneVO droneVO = new DroneVO();
		BeanUtils.copyProperties(drone, droneVO);
		droneService.deleteById(id);
		messageInfo.setCode(HttpStatus.OK.toString());
		messageInfo.setStatus(HttpStatus.OK);
		messageInfo.setMessage("Drone with model " + drone.get().getModel() + " Success delete");
		messageInfo.setResult(droneVO);
		return new ResponseEntity<MessageInfo>(messageInfo, HttpStatus.OK);
	}
}
