package com.practice.library.controoller;

import com.practice.library.dto.MemberRequestDTO;
import com.practice.library.model.Member;
import com.practice.library.service.interfaces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Member> addMember(@RequestBody MemberRequestDTO memberRequestDTO) {
        return ResponseEntity.ok().body(memberService.addMember(memberRequestDTO));
    }

    @GetMapping("/members/{name}")
    public ResponseEntity <List> getMembersByName(@PathVariable String name) {
        return ResponseEntity.ok().body(memberService.getMemberListByName(name));
    }
}
