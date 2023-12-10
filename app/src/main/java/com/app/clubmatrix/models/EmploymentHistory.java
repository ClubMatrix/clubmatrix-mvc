package com.app.clubmatrix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employment_history")
public class EmploymentHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "company_name", nullable = false)
  private String company;

  @Column(nullable = false)
  private String position;

  @Column(name = "start_date", nullable = false)
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;
}
