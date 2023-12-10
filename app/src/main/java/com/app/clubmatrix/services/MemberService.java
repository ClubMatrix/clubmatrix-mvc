package com.app.clubmatrix.services;

import com.app.clubmatrix.models.Member;
import com.app.clubmatrix.models.User;
import com.app.clubmatrix.repositories.MemberRepository;
import com.app.clubmatrix.services.dto.MemberRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserService userService;

    public Member registerMember(MemberRegistrationDTO memberDTO) {
        User newUser = userService.createUser(memberDTO.getCredentials());

        Member newMember = new Member();
        newMember.setName(memberDTO.getName());
        newMember.setAddress(memberDTO.getAddress());
        newMember.setPhone(memberDTO.getPhone());
        newMember.setEmail(memberDTO.getEmail());
        newMember.setUser(newUser);

        try {
            return memberRepository.save(newMember);
        } catch (Exception e) {
            userService.deleteUser(newUser);

            throw e;
        }
    }

    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public void deleteMember(Member member) {
        memberRepository.delete(member);
    }
}
