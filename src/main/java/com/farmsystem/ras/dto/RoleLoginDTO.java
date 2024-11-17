package com.farmsystem.ras.dto;

import lombok.Data;

@Data
public class RoleLoginDTO { // role 다룰 일이 있을 때 필요한 로그인입니다.
    String username;
    String password;
    String role; // USER 또는 ADMIN 두 종류가 있습니다.
}
