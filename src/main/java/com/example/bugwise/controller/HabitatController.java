package com.example.bugwise.controller;

import com.example.bugwise.dto.HabitatDTO;
import com.example.bugwise.entity.Habitat;
import com.example.bugwise.service.HabitatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitats")
public class HabitatController {
    private final HabitatService habitatService;
     @Autowired
    public HabitatController(HabitatService habitatService) {
        this.habitatService = habitatService;
    }
    @GetMapping
    public ResponseEntity<List<HabitatDTO>> getAllHabitats(){
         return ResponseEntity.ok(habitatService.getAllHabitats());
    }
    @GetMapping("/{id}")
    public ResponseEntity<HabitatDTO> getHabitatById(@PathVariable Long id){
         return ResponseEntity.ok(habitatService.findByIdHabitat(id));
    }
    @PostMapping
    public ResponseEntity<HabitatDTO> addHabitat(@RequestBody Habitat habitat){
         return ResponseEntity.status(HttpStatus.CREATED).body(habitatService.addHabitat(habitat));
    }
    @PutMapping("/{id}")
public ResponseEntity<HabitatDTO> updateHabitat(@PathVariable Long id,@RequestBody Habitat habitat) {
        return ResponseEntity.ok(habitatService.updatedHabitat(id, habitat));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitat(@PathVariable Long id){
         habitatService.deleteHabitat(id);
         return ResponseEntity.noContent().build();
    }
}
