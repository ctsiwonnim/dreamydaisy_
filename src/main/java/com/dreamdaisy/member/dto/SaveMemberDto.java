package com.dreamdaisy.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SaveMemberDto {

    @Email // email 형태인지 검증
    @NotBlank // "", " ", NULL 다 비허용
    private String email;

    @NotBlank(message = "이름은 필수값입니다.")
    private String name;

    // 최소 8자, 최대 16자
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;

    @NotBlank(message = "핸드폰 번호는 필수값입니다.")
    private String phone;
}
