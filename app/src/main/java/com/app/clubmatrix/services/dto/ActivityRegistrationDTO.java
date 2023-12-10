package com.app.clubmatrix.services.dto;

import com.app.clubmatrix.models.AgeGroupType;
import com.app.clubmatrix.models.SkillLevelType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActivityRegistrationDTO {

    private String name;
    private String description;
    private AgeGroupType ageGroup;
    private SkillLevelType skillLevel;
}
