package com.app.clubmatrix.models;

import java.util.Set;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classes")
public class ActivityClass {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "activity_id")
  private Activity activity;

  @OneToMany(mappedBy = "activityClass")
  private Set<ActivityClassSchedule> schedules;

  @OneToMany(mappedBy = "activityClass")
  private Set<Enrollment> enrollments;
}
