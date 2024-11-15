package com.mulmeong.member.read.member.application;

import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.event.member.MemberNicknameUpdateEvent;
import com.mulmeong.event.member.MemberProfileImgUpdateEvent;
import com.mulmeong.member.read.member.document.Member;
import com.mulmeong.member.read.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MongoTemplate mongoTemplate;

    /**
     * 회원 Read DB 생성.
     *
     * @param event 회원 생성 Event
     */
    @Override
    public void createMember(MemberCreateEvent event) {

        memberRepository.save(event.toEntity());
    }

    /**
     * 회원 Read DB의 닉네임 수정.
     *
     * @param event 닉네임 수정 Event
     */
    @Override
    public void updateNickname(MemberNicknameUpdateEvent event) {
        Query query = new Query(Criteria.where("memberUuid").is(event.getMemberUuid()));
        Update update = new Update().set("nickname", event.getNickname());

        mongoTemplate.updateFirst(query, update, Member.class);
    }

    /**
     * 회원 Read DB의 프로필 이미지 수정.
     *
     * @param event 프로필 이미지 수정 Event.
     */
    public void updateProfileImage(MemberProfileImgUpdateEvent event) {
        Query query = new Query(Criteria.where("memberUuid").is(event.getMemberUuid()));
        Update update = new Update().set("profileImageUrl", event.getProfileImgUrl());

        mongoTemplate.updateFirst(query, update, Member.class);
    }
}
