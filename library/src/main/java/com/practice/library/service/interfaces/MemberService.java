package com.practice.library.service.interfaces;

import com.practice.library.dto.MemberRequestDTO;
import com.practice.library.model.Member;

import java.util.List;

public interface MemberService {
    Member addMember(MemberRequestDTO memberRequestDTO);
    List<Member> getMemberListByName(String memberName);
}
