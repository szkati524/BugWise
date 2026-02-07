package com.example.bugwise.dto;

import java.util.List;

public record InsectDTO(
        Long id,
        String commonName,
        String latinName,
        String englishName,
        String description,
        String orderName,
        String familyName,
        String habitatName,
        List<String> imageUrls,
        List<String> tags,
        boolean isProtected,
        String dangerLever,
        String dangerLevelCode
) {}

