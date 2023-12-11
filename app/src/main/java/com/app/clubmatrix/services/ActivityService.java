package com.app.clubmatrix.services;

import com.app.clubmatrix.models.Activity;
import com.app.clubmatrix.repositories.ActivityRepository;
import com.app.clubmatrix.services.dto.ActivityRegistrationDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

  @Autowired
  private ActivityRepository ActivityRepository;

  public Activity registerActivity(ActivityRegistrationDTO ActivityDTO) {
    Activity activity = new Activity();
    activity.setName(ActivityDTO.getName());
    activity.setDescription(ActivityDTO.getDescription());
    activity.setAgeGroup(ActivityDTO.getAgeGroup());
    activity.setSkillLevel(ActivityDTO.getSkillLevel());

    return ActivityRepository.save(activity);
  }

  public Activity updateActivity(Activity Activity) {
    return ActivityRepository.save(Activity);
  }

  public List<Activity> getAllActivities() {
    return ActivityRepository.findAll();
  }

  public void deleteActivity(Activity activity) {
    ActivityRepository.delete(activity);
  }
}
