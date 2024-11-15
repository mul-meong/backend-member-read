package com.mulmeong.member.read.member.application;

import com.mulmeong.event.member.MemberCreateDto;
import com.mulmeong.event.member.NicknameUpdateDto;

public interface MemberService {

    void createMember(MemberCreateDto memberCreateDto);

    void updateNickname(NicknameUpdateDto nicknameUpdateDto);

}
