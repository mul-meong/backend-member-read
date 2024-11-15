package com.mulmeong.member.read.member.kafka;

import com.mulmeong.event.member.MemberCreateDto;
import com.mulmeong.event.member.NicknameUpdateDto;
import com.mulmeong.member.read.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    private final MemberService memberService;

    @KafkaListener(topics = "member-created", groupId = "member-read",
            containerFactory = "memberCreateDtoListener")
    public void handleMemberCreatedEvent(MemberCreateDto event) {
        log.info("MemberCreatedEvent Consume : {}", event);
        memberService.createMember(event);
    }

    @KafkaListener(topics = "nickname-updated", groupId = "member-read",
            containerFactory = "nicknameUpdateDtoListener")
    public void handleNicknameUpdatedEvent(NicknameUpdateDto event) {
        log.info("NicknameUpdatedEvent Consume : {}", event);
        memberService.updateNickname(event);
    }
}
