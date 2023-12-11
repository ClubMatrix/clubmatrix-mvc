package com.app.clubmatrix.repositories;

import com.app.clubmatrix.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {}
