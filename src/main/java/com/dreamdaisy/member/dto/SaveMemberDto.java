package com.dreamdaisy.member.dto;

import lombok.Data;

@Data
public class SaveMemberDto {
    private String email;
    private String name;
    private String password;
    private String phone;
}
