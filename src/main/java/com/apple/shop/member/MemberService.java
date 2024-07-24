package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member addMember(Member member) throws Exception {

        var result = memberRepository.findByUsername(member.getUsername());
        if(result.isPresent()){
            throw new Exception("이미 존재하는 아이디");
        }
        if(member.getUsername().length() > 3 && member.getUsername().length() < 15){
            member = memberRepository.save(member);
            return member;
        } else {
            throw new Exception("너무 짧음");
        }
    }
}
