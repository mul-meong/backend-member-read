package com.mulmeong.member.read.member.application;

import com.mulmeong.event.member.MemberCreateDto;
import com.mulmeong.event.member.MemberNicknameUpdateDto;
import com.mulmeong.event.member.MemberProfileImgUpdateDto;

public interface MemberService {

    void createMember(MemberCreateDto memberCreateDto);

    void updateNickname(MemberNicknameUpdateDto memberNicknameUpdateDto);

    void updateProfileImage(MemberProfileImgUpdateDto memberProfileImgUpdateDto);

}
