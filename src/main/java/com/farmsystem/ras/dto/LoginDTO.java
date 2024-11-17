package com.farmsystem.ras.dto;

import lombok.Data;

@Data
public class LoginDTO { // role 입력하지 않는 일반적인 로그인입니다.
    String username;
    String password;
}
