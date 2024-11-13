package com.apple.shop.member;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDTO memberToMemberDTO(Member member);
}
