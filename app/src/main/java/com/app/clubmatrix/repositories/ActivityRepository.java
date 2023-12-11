package com.app.clubmatrix.repositories;

import com.app.clubmatrix.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {}
