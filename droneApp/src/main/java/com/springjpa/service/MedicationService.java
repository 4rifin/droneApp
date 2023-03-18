package com.springjpa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.constant.StateConstant;
import com.springjpa.model.Medication;
import com.springjpa.repository.MedicationRepository;
import com.springjpa.vo.MedicationVO;

@Service
@Transactional
public class MedicationService {
	
	@Autowired 
	MedicationRepository medicationRepository;
	
	public List<Medication> listMedication(){
		List<Medication> medicationList =  (List<Medication>) medicationRepository.findAll();
		return medicationList;
	}
	
	public Optional<Medication> findMedicationById(long id){
		Optional<Medication> medicationList =   medicationRepository.findById(id);
		return medicationList;
	}
	
	public Medication findMedicationByCode(String code){
		Medication medication =   medicationRepository.findByCode(code);
		return medication;
	}

	public void saveMedication(MedicationVO medication) {

		Medication newMedication = new Medication();
		newMedication.setCode(medication.getCode());
		newMedication.setName(medication.getName());
		newMedication.setWeight(medication.getWeight());
		newMedication.setImage(medication.getImage());
		medicationRepository.save(newMedication);
	}
	
	public void deleteById(long id){
		medicationRepository.deleteById(id);
	}
}
