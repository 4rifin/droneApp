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

import com.springjpa.constant.StateConstant;
import com.springjpa.rest.model.MessageInfo;
import com.springjpa.rest.service.DroneRestService;
import com.springjpa.rest.service.MedicationRestService;
import com.springjpa.vo.DroneVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@RequestMapping(value = "/app")
@Api(value="Drone Rest", description = "Api Retrieve Data Drones")
public class DroneRestController {

	static Logger log = Logger.getLogger(DroneRestController.class.getName());

	@Autowired
	DroneRestService droneRestService;
	
	@Autowired
	MedicationRestService medicationRestService;

	// --------------------------------------------------------
	// ----------------Retrieve All Drones------------------
	// --------------------------------------------------------
	@ApiOperation(value = "Get All Data Drones")
	@RequestMapping(value = "/droneGetAll", method = RequestMethod.GET)
	public ResponseEntity<MessageInfo> listDroneGetAll(@Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = droneRestService.droneGetAll(messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	// --------------------------------------------------------
	// ----------------Retrieve Single Drone---------------
	// --------------------------------------------------------
	@ApiOperation(value = "Get Data Drone By id")
	@RequestMapping(value = "/droneGetId/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> listDrones(@PathVariable("id") long id) {

		try {
			ResponseEntity<String> messageResult = droneRestService.droneGetId(id);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	// --------------------------------------------------------
	// ----------------Retrieve Drone By State---------------
	// --------------------------------------------------------
	@ApiOperation(value = "Get Data Drone By State")
	@RequestMapping(value = "/drone/state/{codeState}", method = RequestMethod.GET)
	public ResponseEntity<String> listDronesByState(@PathVariable("codeState") int codeState) {

		try {
			ResponseEntity<String> messageResult = droneRestService.droneGetByState(StateConstant.getLabelFromCode(codeState));
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	// --------------------------------------------------------
	// ----------------Retrieve Single Drone---------------
	// --------------------------------------------------------
	@ApiOperation(value = "Get Data Drone By level BaterryCapacity")
	@RequestMapping(value = "/drone/battery/from/{levelA}/to/{levelB}", method = RequestMethod.GET)
	public ResponseEntity<String> listDronesByBatteryCapacity(@PathVariable("levelA") int levelA,@PathVariable("levelB") int levelB) {

		try {
			ResponseEntity<String> messageResult = droneRestService.droneGetByBatteryCapacity(levelA, levelB);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	// --------------------------------------------------------
	// ----------------Retrieve Single Drone---------------
	// --------------------------------------------------------
	@ApiOperation(value = "Get Data Drone By serialNumber")
	@RequestMapping(value = "/drone/serialNumber/{serialNumber}", method = RequestMethod.GET)
	public ResponseEntity<String> findDroneBySerialNumber(@PathVariable("serialNumber") String serialNumber) {

		try {
			ResponseEntity<String> messageResult = droneRestService.droneGetSerialNumber(serialNumber);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	// --------------------------------------------------------
	// ----------------Retrieve Single Drone---------------
	// --------------------------------------------------------
	@ApiOperation(value = "Get Data Drone By model")
	@RequestMapping(value = "/drone/model/{model}", method = RequestMethod.GET)
	public ResponseEntity<String> findDroneByModel(@PathVariable("model") String model) {

		try {
			ResponseEntity<String> messageResult = droneRestService.droneGetModel(model);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	// --------------------------------------------------------
	// ----------------Add Drone-----------------------
	// --------------------------------------------------------
	@ApiOperation(value = "add Data Drone")
	@RequestMapping(value = "/drone/add", method = RequestMethod.POST)
	public ResponseEntity<MessageInfo> addDrone(@RequestBody DroneVO droneVO,
			@Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = droneRestService.createDrone(droneVO, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	// --------------------------------------------------------
	// ----------------Update a Customer-----------------------
	// --------------------------------------------------------
	@ApiOperation(value = "Edit Data Drone")
	@RequestMapping(value = "/drone/edit", method = RequestMethod.PUT)
	public ResponseEntity<MessageInfo> updateDrone(@RequestBody DroneVO droneVO,
			@Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = droneRestService.updateDrone(droneVO, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	// --------------------------------------------------------
	// ----------------Delete a Customer-----------------------
	// --------------------------------------------------------
	@ApiOperation(value = "Delete Data Drone")
	@RequestMapping(value = "/drone/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<MessageInfo> deleteDrone(@PathVariable("id") long id, @Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = droneRestService.deleteDrone(id, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	/*
	 * ========================================================================
	 */
	@ApiOperation(value = "Get All Data Drone list")
	@RequestMapping(value = "/listDrone", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MessageInfo> listDrone(@Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = droneRestService.droneGetAll(messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	@ApiOperation(value = "Get Data Drone By id List ")
	@RequestMapping(value = "/listDrone/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> listDroneById(@PathVariable("id") long id) {

		try {
			ResponseEntity<String> messageResult = droneRestService.droneGetId(id);
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
