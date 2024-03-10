package com.practice.library.service.impl;

import com.practice.library.dto.MemberRequestDTO;
import com.practice.library.model.Member;
import com.practice.library.repository.MemberRepository;
import com.practice.library.service.interfaces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    public Member addMember(MemberRequestDTO memberRequestDTO) {
        Member member = new Member();
        member.setnId(memberRequestDTO.getnId());
        member.setName(memberRequestDTO.getName());
        member.setPhoneNumber(memberRequestDTO.getPhoneNumber());
        return memberRepository.addMember(member);
    }

    public List<Member> getMemberListByName(String memberName) {
        return (List<Member>) memberRepository.getMemberListByName(memberName);
    }
}
