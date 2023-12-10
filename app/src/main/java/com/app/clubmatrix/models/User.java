package com.app.clubmatrix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "user")
  private Set<Notification> notifications;

  @ManyToMany
  @JoinTable(name = "user_group_memberships", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<Group> groups;

  @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
  private Member member;

  @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
  private Employee employee;
}
