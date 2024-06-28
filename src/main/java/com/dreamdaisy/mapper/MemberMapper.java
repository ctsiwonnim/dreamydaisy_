package com.dreamdaisy.mapper;

import com.dreamdaisy.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Member findById(Long id);

    List<Member> findAll();

}
