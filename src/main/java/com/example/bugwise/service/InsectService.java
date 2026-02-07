package com.example.bugwise.service;

import com.example.bugwise.dto.InsectDTO;
import com.example.bugwise.entity.Insect;
import com.example.bugwise.entity.InsectImage;
import com.example.bugwise.entity.Tag;
import com.example.bugwise.repository.InsectRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsectService {

    private final InsectRepository insectRepository;

    @Autowired
    public InsectService(InsectRepository insectRepository) {
        this.insectRepository = insectRepository;
    }
    @Transactional
    public InsectDTO addInsect(Insect insect){
       Insect savedInsect = insectRepository.save(insect);
       return mapToDTO(savedInsect);
    }
    public List<InsectDTO> getAllInsects(){
        return insectRepository.findAll().stream().map(this::mapToDTO).toList();
    }
    public InsectDTO getInsectById(Long id){
        return insectRepository.findById(id).map(this::mapToDTO).orElseThrow(() -> new EntityNotFoundException("Insect with id " + id + " not found"));

    }
    @Transactional
    public InsectDTO updateInsect(Long id,Insect insectDetails){
Insect insect = insectRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Insect with id " + id + " not found"));
insect.setCommonName(insectDetails.getCommonName());
insect.setLatinName(insectDetails.getLatinName());
insect.setEnglishName(insectDetails.getEnglishName());
insect.setDescription(insectDetails.getDescription());
insect.setInsectOrder(insectDetails.getInsectOrder());
insect.setInsectFamily(insectDetails.getInsectFamily());
insect.setInsectImage(insectDetails.getInsectImage());
insect.setHabitat(insectDetails.getHabitat());
insect.setTag(insectDetails.getTag());
insect.setDangerLevel(insectDetails.getDangerLevel());
Insect updatedInsect = insectRepository.save(insect);
return mapToDTO(updatedInsect);
    }
    @Transactional
    public void deleteInsect(Long id){
        insectRepository.deleteById(id);
    }
    private InsectDTO mapToDTO(Insect insect){
        return  new InsectDTO(
                insect.getId(),
                insect.getCommonName(),
                insect.getLatinName(),
                insect.getEnglishName(),
                insect.getDescription(),
                insect.getInsectOrder() != null ? insect.getInsectOrder().getName() : "unknown",
                insect.getInsectFamily() != null ? insect.getInsectFamily().getName() : "unknown",
                insect.getHabitat() != null ? insect.getHabitat().getName() : "unknown",
                insect.getInsectImage() != null ?
                        insect.getInsectImage().stream().map(InsectImage::getUrl).toList() : List.of(),
                insect.getTag() != null ?
                        insect.getTag().stream().map(Tag::getName).toList() : List.of(),
                insect.isProtected(),
                insect.getDangerLevel() != null ? insect.getDangerLevel().name() : "unknown",
                insect.getDangerLevel() != null ? insect.getDangerLevel().name() : null
        );
    }

}
