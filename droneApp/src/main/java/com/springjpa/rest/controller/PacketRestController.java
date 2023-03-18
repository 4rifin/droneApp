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
import com.springjpa.rest.service.PacketRestService;
import com.springjpa.vo.PacketVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@RequestMapping(value = "/app")
@Api(value="Packet Rest", description = "Api Retrieve Data Packet")
public class PacketRestController {

	static Logger log = Logger.getLogger(PacketRestController.class.getName());

	@Autowired
	PacketRestService packetRestService;
	
	@Autowired
	MedicationRestService medicationRestService;

	// --------------------------------------------------------
	// ----------------Retrieve All Packets------------------
	// --------------------------------------------------------
	@ApiOperation(value = "Get All Data Packets")
	@RequestMapping(value = "/packetGetAll", method = RequestMethod.GET)
	public ResponseEntity<MessageInfo> listPacketGetAll(@Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = packetRestService.packetGetAll(messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	// --------------------------------------------------------
	// ----------------Retrieve Single Packet---------------
	// --------------------------------------------------------
	@ApiOperation(value = "Get Data Packet By id")
	@RequestMapping(value = "/packetGetId/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> listPackets(@PathVariable("id") long id) {

		try {
			ResponseEntity<String> messageResult = packetRestService.packetGetId(id);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	// --------------------------------------------------------
	// ----------------Retrieve Single Packet---------------
	// --------------------------------------------------------
	@ApiOperation(value = "Get Data Packet By packetCode")
	@RequestMapping(value = "/packet/packetCode/{packetCode}", method = RequestMethod.GET)
	public ResponseEntity<String> findPacketBySerialNumber(@PathVariable("packetCode") String packetCode) {

		try {
			ResponseEntity<String> messageResult = packetRestService.packetGetBypacketCode(packetCode);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	// --------------------------------------------------------
	// ----------------Add Packet-----------------------
	// --------------------------------------------------------
	@ApiOperation(value = "add Data Packet")
	@RequestMapping(value = "/packet/add", method = RequestMethod.POST)
	public ResponseEntity<MessageInfo> addPacket(@RequestBody PacketVO packetVO,
			@Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = packetRestService.createPacket(packetVO, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	// --------------------------------------------------------
	// ----------------Update a Customer-----------------------
	// --------------------------------------------------------
	@ApiOperation(value = "Edit Data Packet")
	@RequestMapping(value = "/packet/edit", method = RequestMethod.PUT)
	public ResponseEntity<MessageInfo> updatePacket(@RequestBody PacketVO packetVO,
			@Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = packetRestService.updatePacket(packetVO, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	// --------------------------------------------------------
	// ----------------Update a Customer-----------------------
	// --------------------------------------------------------
	@ApiOperation(value = "Update Status State Data Packet")
	@RequestMapping(value = "/packet/update/state", method = RequestMethod.PUT)
	public ResponseEntity<MessageInfo> updateStatePacket(@RequestBody PacketVO packetVO, @Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = packetRestService.updatePacket(packetVO, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	// --------------------------------------------------------
	// ----------------Delete a Customer-----------------------
	// --------------------------------------------------------
	@ApiOperation(value = "Delete Data Packet")
	@RequestMapping(value = "/packet/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<MessageInfo> deletePacket(@PathVariable("id") long id, @Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = packetRestService.deletePacket(id, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	/*
	 * ========================================================================
	 */
	@ApiOperation(value = "Get All Data Packet list")
	@RequestMapping(value = "/listPacket", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MessageInfo> listPacket(@Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = packetRestService.packetGetAll(messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	@ApiOperation(value = "Get Data Packet By id List ")
	@RequestMapping(value = "/listPacket/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> listPacketById(@PathVariable("id") long id) {

		try {
			ResponseEntity<String> messageResult = packetRestService.packetGetId(id);
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
