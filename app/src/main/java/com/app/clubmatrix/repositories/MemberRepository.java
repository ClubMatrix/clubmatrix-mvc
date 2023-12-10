package com.app.clubmatrix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.clubmatrix.models.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
