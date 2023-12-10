package com.app.clubmatrix.models;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_card_payments")
public class CreditCardPayment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 255)
  private String cardNumber;

  @Column(name = "card_expiry", nullable = false)
  private Date cardExpiry;

  @Column(name = "card_cvv", length = 10)
  private String cardCvv;

  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @OneToOne
  @JoinColumn(name = "member_id")
  private Member member;
}
