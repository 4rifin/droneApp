package com.springjpa.rest.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.rest.model.MessageInfo;
import com.springjpa.rest.service.MedicationRestService;
import com.springjpa.vo.DroneVO;
import com.springjpa.vo.MedicationVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@RequestMapping(value = "/app")
@Api(value="Drone Rest", description = "Api Retrieve Data Drones")
public class MedicationRestController {

	static Logger log = Logger.getLogger(MedicationRestController.class.getName());
	
	@Autowired
	MedicationRestService medicationRestService;

	
	// --------------------------------------------------------
	// ----------------Retrieve Single Drone---------------
	// --------------------------------------------------------
	@ApiOperation(value = "Get Data Medication By id")
	@RequestMapping(value = "/medicationGetId/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> listDrones(@PathVariable("id") long id) {

		try {
			ResponseEntity<String> messageResult = medicationRestService.medicationGetId(id);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	// --------------------------------------------------------
	// ----------------Retrieve Single Drone---------------
	// --------------------------------------------------------
	@ApiOperation(value = "Get Data Medicaiton By code")
	@RequestMapping(value = "/medication/code/{code}", method = RequestMethod.GET)
	public ResponseEntity<String> findDroneBySerialNumber(@PathVariable("code") String code) {

		try {
			ResponseEntity<String> messageResult = medicationRestService.medicationGetCode(code);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	// --------------------------------------------------------
	// ----------------Add Medication-----------------------
	// --------------------------------------------------------
	@ApiOperation(value = "add Data Medication")
	@RequestMapping(value = "/medication/add", method = RequestMethod.POST)
	public ResponseEntity<MessageInfo> addMedication(@RequestBody MedicationVO medicationVO,
			@Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = medicationRestService.createMedication(medicationVO, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	// --------------------------------------------------------
	// ----------------Delete a Customer-----------------------
	// --------------------------------------------------------
	@ApiOperation(value = "Delete Data Medication")
	@RequestMapping(value = "/medication/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<MessageInfo> deleteDrone(@PathVariable("id") long id, @Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = medicationRestService.deleteMedication(id, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	/*
	 * ========================================================================
	 */
	@ApiOperation(value = "Get All Data Medication list")
	@RequestMapping(value = "/listMedication", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MessageInfo> listDrone(@Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = medicationRestService.medicationGetAll(messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	// --------------------------------------------------------
	// ----------------Retrieve All Drones------------------
	// --------------------------------------------------------
	@ApiOperation(value = "Get All Data Medications")
	@RequestMapping(value = "/medicationGetAll", method = RequestMethod.GET)
	public ResponseEntity<MessageInfo> listMedicationGetAll(@Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = medicationRestService.medicationGetAll(messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Can't add yourself") // 403
	public class UserIsSelfException extends RuntimeException {
		private static final long serialVersionUID = -6871307095006922960L;

		public UserIsSelfException(String message) {
			super(message);
		}
	}
}
