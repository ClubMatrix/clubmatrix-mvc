package com.app.clubmatrix.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "members")
public class Member {

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

  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "member")
  private Set<Enrollment> enrollments;

  @OneToMany(mappedBy = "member")
  private Set<BankTransfer> bankTransfers;

  @OneToMany(mappedBy = "member")
  private Set<CreditCardPayment> creditCardPayments;

  @OneToMany(mappedBy = "member")
  private Set<Dependent> dependents;

  @OneToMany(mappedBy = "member")
  private Set<Order> orders;
}
