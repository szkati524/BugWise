package com.example.bugwise.service;

import com.example.bugwise.dto.InsectFamilyDTO;
import com.example.bugwise.dto.InsectOrderDTO;
import com.example.bugwise.entity.InsectFamily;
import com.example.bugwise.repository.InsectFamilyRepository;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsectFamilyService {
    private final InsectFamilyRepository insectFamilyRepository;

    public InsectFamilyService(InsectFamilyRepository insectFamilyRepository) {
        this.insectFamilyRepository = insectFamilyRepository;
    }
    public List<InsectFamilyDTO> getAllInsectFamily(){
        return insectFamilyRepository.findAll().stream().map(this::mapToDTO).toList();
    }
    public InsectFamilyDTO findInsectFamilyById(Long id){
        return insectFamilyRepository.findById(id).map(this::mapToDTO).orElseThrow(() -> new EntityNotFoundException("family with id " + id + " not found"));
    }
    @Transactional
    public InsectFamilyDTO addInsectFamily(InsectFamily insectFamily){
        InsectFamily savedInsectFamily = insectFamilyRepository.save(insectFamily);
        return mapToDTO(savedInsectFamily);
    }
    @Transactional
    public InsectFamilyDTO updateInsectFamily(Long id,InsectFamily familyDetails){
        InsectFamily family = insectFamilyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("family with id " + id + " not found"));
        family.setName(familyDetails.getName());
        family.setLatinName(familyDetails.getLatinName());
        InsectFamily updatedFamily = insectFamilyRepository.save(family);
        return mapToDTO(updatedFamily);
    }
    @Transactional
    public void deleteInsectFamily(Long id){
        if (!insectFamilyRepository.existsById(id)){
            throw new EntityNotFoundException("insect with id " + id + " not found");
        }
        insectFamilyRepository.deleteById(id);
    }
        private InsectFamilyDTO mapToDTO(InsectFamily insectFamily){
        return new InsectFamilyDTO(
                insectFamily.getId(),
                insectFamily.getName(),
                insectFamily.getLatinName()
        );
    }
}
