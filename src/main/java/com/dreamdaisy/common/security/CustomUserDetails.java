package com.dreamdaisy.common.security;

import com.dreamdaisy.member.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한을 반환합니다. 여기서는 기본적으로 빈 리스트를 반환합니다.
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        // 사용자 비밀번호를 반환합니다.
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        // 사용자 이름을 반환합니다.
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정이 만료되지 않았는지 여부를 반환합니다.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠겨있지 않은지 여부를 반환합니다.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명이 만료되지 않았는지 여부를 반환합니다.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정이 활성화되어 있는지 여부를 반환합니다.
        return true;
    }
}
