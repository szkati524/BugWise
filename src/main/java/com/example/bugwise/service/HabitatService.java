package com.example.bugwise.service;

import com.example.bugwise.dto.HabitatDTO;
import com.example.bugwise.entity.Habitat;
import com.example.bugwise.repository.HabitatRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitatService {
    private final HabitatRepository habitatRepository;
@Autowired
    public HabitatService(HabitatRepository habitatRepository) {
        this.habitatRepository = habitatRepository;
    }
    public List<HabitatDTO> getAllHabitats(){
    return habitatRepository.findAll().stream().map(this::mapToDTO).toList();

    }
    public HabitatDTO findByIdHabitat(Long id){
    return habitatRepository.findById(id).map(this::mapToDTO).orElseThrow(() -> new EntityNotFoundException("habitat with id " + id + " not found"));
    }
    @Transactional
    public HabitatDTO addHabitat(Habitat habitat){
    Habitat savedHabitat = habitatRepository.save(habitat);
    return mapToDTO(savedHabitat);
    }
    @Transactional
    public HabitatDTO updatedHabitat(Long id,Habitat habitatDetails){
    Habitat habitat = habitatRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("habitat with id " + id + " not found"));
    habitat.setName(habitatDetails.getName());
    habitat.setType(habitatDetails.getType());
    habitat.setClimateDescription(habitatDetails.getClimateDescription());
    Habitat updatedHabitat = habitatRepository.save(habitat);
    return mapToDTO(updatedHabitat);
    }
    @Transactional
    public void deleteHabitat(Long id){
    if (!habitatRepository.existsById(id)){
        throw new EntityNotFoundException("insect with id " + id + " not found");
    }
    habitatRepository.deleteById(id);
    }

    private HabitatDTO mapToDTO(Habitat habitat){
        return new HabitatDTO(habitat.getId(),
         habitat.getName(),
        habitat.getType(),
        habitat.getClimateDescription()
                 );
    }
}
