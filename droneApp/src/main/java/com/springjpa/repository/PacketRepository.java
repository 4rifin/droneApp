package com.springjpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springjpa.model.Packet;

public interface PacketRepository extends CrudRepository<Packet, Long>{
	
	public Optional<Packet> findById(Long id);
	@Query("select u from Packet u where u.id = ?1")
	public Packet findByPacketId(long id);
	@Query("select u from Packet u where u.packetCode = ?1")
	public List <Packet> findByPacketCode(String packetCode);
}
