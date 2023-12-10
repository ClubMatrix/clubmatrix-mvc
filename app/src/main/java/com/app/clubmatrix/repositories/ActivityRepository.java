package com.app.clubmatrix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.clubmatrix.models.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}