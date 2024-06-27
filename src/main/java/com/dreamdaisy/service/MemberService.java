package com.dreamdaisy.service;

import com.dreamdaisy.domain.Member;
import com.dreamdaisy.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    @Transactional
    public void save(Member member) {
        memberMapper.save(member);
    }
}
