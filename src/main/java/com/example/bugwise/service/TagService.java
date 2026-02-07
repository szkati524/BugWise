package com.example.bugwise.service;

import com.example.bugwise.dto.TagDTO;
import com.example.bugwise.entity.Tag;
import com.example.bugwise.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;
@Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    public List<TagDTO> getAllTags(){
        return tagRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public TagDTO findByTagId(Long id){
        return tagRepository.findById(id).map(this::mapToDTO).orElseThrow(() -> new EntityNotFoundException("tag with id " + id + " not found"));
    }

    @Transactional
    public TagDTO addTag(Tag tag){
        Tag savedTag = tagRepository.save(tag);
        return mapToDTO(savedTag);
    }
    @Transactional
    public void deleteTag(Long id){
        tagRepository.deleteById(id);
    }
    private TagDTO mapToDTO(Tag tag ){
        return new TagDTO(tag.getId(),tag.getName());
    }
}
