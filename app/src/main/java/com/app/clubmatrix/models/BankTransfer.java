package com.app.clubmatrix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_transfers")
public class BankTransfer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String bankName;

  @Column(nullable = false)
  private String accountNumber;

  @Column
  private String routingNumber;

  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @OneToOne
  @JoinColumn(name = "member_id")
  private Member member;
}
