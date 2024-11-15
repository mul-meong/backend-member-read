package com.mulmeong.member.read.member.application;

import com.mulmeong.event.member.MemberCreateDto;
import com.mulmeong.event.member.MemberNicknameUpdateDto;
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
     * @param dto 회원 생성 DTO
     */
    @Override
    public void createMember(MemberCreateDto dto) {

        memberRepository.save(dto.toEntity());
    }

    /**
     * 회원 Read DB의 닉네임 수정.
     *
     * @param dto 닉네임 수정 DTO
     */
    @Override
    public void updateNickname(MemberNicknameUpdateDto dto) {
        Query query = new Query(Criteria.where("memberUuid").is(dto.getMemberUuid()));
        Update update = new Update().set("nickname", dto.getNickname());

        mongoTemplate.updateFirst(query, update, Member.class); // 필요한 필드만 업데이트
    }
}
