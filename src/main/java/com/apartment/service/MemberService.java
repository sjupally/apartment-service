package com.apartment.service;

import com.apartment.entity.Member;
import com.apartment.entity.Unit;
import com.apartment.repositories.MemberRepository;
import com.apartment.repositories.UnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberRepository memberRepository;

    public Member save(Member member) {
        LOGGER.info("MemberService:::save:::Method Start End");
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        LOGGER.info("MemberService:::findAll:::Method Start End");
        return (List<Member>) memberRepository.findAll();
    }

    public Member findById(long id) {
        LOGGER.info("MemberService:::findById:::Method Start End");
        return memberRepository.findById(id).get();
    }

    public void update(Member member) {
        LOGGER.info("MemberService:::update:::Method Start End");
        memberRepository.save(member);
    }
}
