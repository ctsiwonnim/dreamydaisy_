package com.dreamdaisy.member.mapper;

import com.dreamdaisy.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Member findById(Long id);

    List<Member> findAll();

    Member findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    Member findByEmail(@Param("email") String email);

    void update(Member member);

    void delete(Long id);
}
