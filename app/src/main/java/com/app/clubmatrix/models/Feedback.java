package com.app.clubmatrix.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedback {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String feedback;

  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "given_to_id")
  private Employee employee;

  @ManyToOne
  @JoinColumn(name = "given_by_id")
  private Employee givenBy;
}
