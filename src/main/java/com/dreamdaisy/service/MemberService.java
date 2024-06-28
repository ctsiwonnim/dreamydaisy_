package com.dreamdaisy.service;

import com.dreamdaisy.domain.Member;
import com.dreamdaisy.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberMapper memberMapper;


    @Transactional
    public void save(Member member) {

        memberMapper.save(member);
    }

    public Member findById(Long id) {
        return memberMapper.findById(id);
    }

    public List<Member> findAll() {
        return memberMapper.findAll();
    }


}
