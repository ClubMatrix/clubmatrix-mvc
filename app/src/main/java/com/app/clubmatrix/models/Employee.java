package com.app.clubmatrix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false, length = 20)
  private String phone;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private PositionType position;

  @Column(nullable = false)
  private Integer salary;

  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Enumerated
  @OneToMany(mappedBy = "employee")
  private Set<EmploymentHistory> employmentHistories;

  @OneToMany
  @JoinColumn(name = "given_to_id")
  private Set<Feedback> feedbacks;

  @OneToMany
  @JoinColumn(name = "given_by_id")
  private Set<Feedback> givenFeedbacks;

  @OneToMany(mappedBy = "employee")
  private Set<Training> trainings;

  @OneToMany(mappedBy = "employee")
  private Set<Order> orders;
}
