package com.springjpa.rest.service;

import java.util.List;
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
import com.springjpa.model.Drone;
import com.springjpa.model.Medication;
import com.springjpa.repository.MedicationRepository;
import com.springjpa.rest.model.MessageInfo;
import com.springjpa.service.MedicationService;
import com.springjpa.vo.DroneVO;
import com.springjpa.vo.MedicationVO;

@Service
@Transactional
public class MedicationRestService {
	
	@Autowired 
	MedicationRepository medicationRepository;
	@Autowired 
	MedicationService medicationService;
	
	public ResponseEntity<MessageInfo> medicationGetAll(MessageInfo messageInfo) {

		List<Medication> droneList = medicationService.listMedication();
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
	
	public ResponseEntity<MessageInfo> createMedication(MedicationVO medicationVO,MessageInfo messageInfo) {
		Medication getMedicationByCode = medicationService.findMedicationByCode(medicationVO.getCode());
		
		if (getMedicationByCode != null) {
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Duplicate Medication of drone");
			return new ResponseEntity<>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}
		//allowed only letters, numbers, ‘-‘, ‘_’
		String regex = "^[a-zA-Z0-9_-]*$";
		if(!medicationVO.getName().matches(regex)) {
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Name Medication allowed only letters, numbers, ‘-‘, ‘_’");
			return new ResponseEntity<>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}

		// allowed only upper case letters, underscore and numbers
		String regexTwo = "^[A-Z0-9_-]*$";
		if (!medicationVO.getCode().matches(regexTwo)) {
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Code Medication allowed only upper case letters, underscore and numbers");
			return new ResponseEntity<>(messageInfo, HttpStatus.ALREADY_REPORTED);
		}
	
		medicationService.saveMedication(medicationVO);
		
		MedicationVO newMedicationVO = new MedicationVO();
		//Drone drone = droneService.findBySerialNumber(droneVO.getSerialNumber());
		BeanUtils.copyProperties(medicationVO, newMedicationVO);
		
		messageInfo.setCode(HttpStatus.CREATED.toString());
		messageInfo.setStatus(HttpStatus.CREATED);
		messageInfo.setMessage("Success");
		messageInfo.setResult(newMedicationVO.toString());
		return new ResponseEntity<>(messageInfo, HttpStatus.CREATED);
	}
	
	public ResponseEntity<String> medicationGetCode(String code) {

		Medication droneBySerialNumber = medicationService.findMedicationByCode(code);
		MedicationVO medicationVO = new MedicationVO();
		BeanUtils.copyProperties(droneBySerialNumber, medicationVO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		ResponseEntity<String> responseEntity = null;

		String body = "";
		Gson gson = new Gson();
		if (droneBySerialNumber == null) {
			body = "null";
		} else {
			body = gson.toJson(medicationVO);
		}

		if (droneBySerialNumber == null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.NOT_FOUND);
		}

		if (droneBySerialNumber != null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.OK);
		}

		return responseEntity;
	}
	
	public ResponseEntity<String> medicationGetId(long id) {

		Optional<Medication> medicationById = medicationService.findMedicationById(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		ResponseEntity<String> responseEntity = null;

		String body = "";
		Gson gson = new Gson();
		if (medicationById == null) {
			body = "null";
		} else {
			body = gson.toJson(medicationById);
		}

		if (medicationById == null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.NOT_FOUND);
		}

		if (medicationById != null) {
			responseEntity = new ResponseEntity<String>(body, headers, HttpStatus.OK);
		}

		return responseEntity;
	}
	
	public ResponseEntity<MessageInfo> deleteMedication(long id, @Valid MessageInfo messageInfo) {

		Optional<Medication> medication = medicationService.findMedicationById(id);
		if (medication == null) {
			System.out.println("disable to delete. Mediacation with Name " + medication.get().getName() + " not found");
			messageInfo.setCode(HttpStatus.ALREADY_REPORTED.toString());
			messageInfo.setStatus(HttpStatus.ALREADY_REPORTED);
			messageInfo.setMessage("Unable to delete. Medication with name " + medication.get().getName() + " not found");
			messageInfo.setResult(medication);
			return new ResponseEntity<MessageInfo>(HttpStatus.ALREADY_REPORTED);
		}
		DroneVO droneVO = new DroneVO();
		BeanUtils.copyProperties(medication, droneVO);
		medicationService.deleteById(id);
		messageInfo.setCode(HttpStatus.OK.toString());
		messageInfo.setStatus(HttpStatus.OK);
		messageInfo.setMessage("medication with code " + medication.get().getCode() + " Success delete");
		messageInfo.setResult(droneVO);
		return new ResponseEntity<MessageInfo>(messageInfo, HttpStatus.OK);
	}

}
