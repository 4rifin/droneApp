package com.springjpa.scheduler.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.constant.StateConstant;
import com.springjpa.constant.StatusConstant;
import com.springjpa.model.Packet;
import com.springjpa.service.DroneService;
import com.springjpa.service.PacketService;

@Service
@Transactional
public class SchedulerService {

	@Autowired
	PacketService packetService;
	@Autowired
	DroneService droneService;

	public void updateStateDroneEvery5minute() {
		List<Packet> packetList = packetService.listPacket();
		if (packetList != null) {

			List<Packet> packetStatusDeliveredingList = packetList.stream()
					.filter(obj -> StatusConstant.DONE.getLabelKey() == obj.getStatus()
							&& StateConstant.DELIVERED.getLabelKey().equalsIgnoreCase(obj.getDrone().getState()))
					.collect(Collectors.toList());
			packetStatusDeliveredingList.stream().forEach(dronePacket -> {
				dronePacket.getDrone().setState(StateConstant.IDLE.getLabelKey());
				dronePacket.getDrone().setBatteryCapacity(dronePacket.getDrone().getBatteryCapacity() - 5);
				packetService.updateStatusDelivaryPacket(dronePacket);
			});
			
		}

	}
	
	public void updateStateDroneEvery4minute() {
		List<Packet> packetList = packetService.listPacket();
		if (packetList != null) {
			List<Packet> packetStatusDeliveredingList = packetList.stream()
					.filter(obj -> StatusConstant.DONE.getLabelKey() == obj.getStatus()
							&& StateConstant.DELIVERED.getLabelKey().equalsIgnoreCase(obj.getDrone().getState()))
					.collect(Collectors.toList());
			packetStatusDeliveredingList.stream().forEach(dronePacket -> {
				dronePacket.getDrone().setState(StateConstant.IDLE.getLabelKey());
				dronePacket.getDrone().setBatteryCapacity(dronePacket.getDrone().getBatteryCapacity() - 10);
				packetService.updateStatusDelivaryPacket(dronePacket);
			});
		}
	}
	
	public void updateStateDroneEvery3minute() {
		List<Packet> packetList = packetService.listPacket();
		if (packetList != null) {
			List<Packet> packetStatusDeliveredingList = packetList.stream()
					.filter(obj -> StatusConstant.DONE.getLabelKey().equals(obj.getStatus())
							&& StateConstant.DELIVERED.getLabelKey().equalsIgnoreCase(obj.getDrone().getState()))
					.collect(Collectors.toList());
			packetStatusDeliveredingList.stream().forEach(dronePacket -> {
				dronePacket.getDrone().setState(StateConstant.IDLE.getLabelKey());
				dronePacket.getDrone().setBatteryCapacity(dronePacket.getDrone().getBatteryCapacity() - 10);
				packetService.updateStatusDelivaryPacket(dronePacket);
			});
		}
	}
	
	public void updateStateDroneEvery2minute() {
		List<Packet> packetList = packetService.listPacket();
		if (packetList != null) {
			List<Packet> packetStatusDeliveringList = packetList.stream()
					.filter(obj -> StatusConstant.PROGRESS.getLabelKey().equals(obj.getStatus())
							&& StateConstant.DELIVERING.getLabelKey().equalsIgnoreCase(obj.getDrone().getState()))
					.collect(Collectors.toList());
			packetStatusDeliveringList.stream().forEach(dronePacket -> {
				dronePacket.getDrone().setState(StateConstant.DELIVERED.getLabelKey());
				dronePacket.setStatus(StatusConstant.DONE.getLabelKey());
				packetService.updateStatusDelivaryPacket(dronePacket);
			});
		}
	}
	
	public void updateStateDroneEvery1minute() {
		List<Packet> packetList = packetService.listPacket();
		if (packetList != null) {
			List<Packet> packetStatusIdleList = packetList.stream()
					.filter(obj -> StatusConstant.READY.getLabelKey().equals(obj.getStatus())
							&& StateConstant.LOADING.getLabelKey().equalsIgnoreCase(obj.getDrone().getState()))
					.collect(Collectors.toList());
			packetStatusIdleList.stream().forEach(dronePacket -> {
				dronePacket.getDrone().setState(StateConstant.LOADED.getLabelKey());
				dronePacket.setStatus(StatusConstant.PROGRESS.getLabelKey());
				packetService.updateStatusDelivaryPacket(dronePacket);
			});
			
			List<Packet> packetStatusLoadingList = packetList.stream()
					.filter(obj -> StatusConstant.PROGRESS.getLabelKey().equals(obj.getStatus())
							&& StateConstant.LOADED.getLabelKey().equalsIgnoreCase(obj.getDrone().getState()))
					.collect(Collectors.toList());
			packetStatusLoadingList.stream().forEach(dronePacket -> {
				dronePacket.getDrone().setState(StateConstant.DELIVERING.getLabelKey());
				dronePacket.setStatus(StatusConstant.PROGRESS.getLabelKey());
				packetService.updateStatusDelivaryPacket(dronePacket);
			});
		}
	}
}
