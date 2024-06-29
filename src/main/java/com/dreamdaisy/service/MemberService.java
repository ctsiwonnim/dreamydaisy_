package com.dreamdaisy.service;

import com.dreamdaisy.domain.Member;
import com.dreamdaisy.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
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

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberMapper.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Transactional(readOnly = true)
    public Member findByEmailAndPassword(String email, String password) {
        Member member = memberMapper.findByEmailAndPassword(email, password);
        System.out.println("*******" + member.getEmail());
        return member;
    }

    @Transactional
    public void update(Member member) {
        memberMapper.update(member);
    }

    @Transactional
    public void delete(Long id) {
        memberMapper.delete(id);
    }

}
