package com.farmsystem.ras.dto;

import lombok.Data;

@Data
public class LoginResponse { // 로그인 성공 및 실패 할 때 반환하는 값 입니다.

    // 로그인이 성공했는지 안했는지 확인하기 위한 상태입니다.
    // 로그인 실패를 할 때 기본 값을 반환하면 됩니다.
    private String loginStatus = "LOGIN FAILED";

    // 어떤 취약점으로 공격을 했는지 확인하기 위한 상태입니다.
    // 로그인 실패를 할 때 기본 값을 반환하면 됩니다.
    private String vulnerablePoint = "LOGIN FAILED";

    // 요청한 ID가 무엇이었는지 담는 변수입니다.
    private String username;

    // 요청한 비밀번호가 무엇이었는지 담는 변수입니다.
    private String password;

    // role 로그인을 할 때 role 상태를 담는 변수입니다.
    // USER 또는 ADMIN 두 종류가 있습니다.
    // 사용하지 않을 때 기본 값을 반환하면 됩니다.
    private String role = "NONE";
}
