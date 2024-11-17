package com.farmsystem.ras.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User { // user DB 입니다.
    // 일반적으로 있는 ID 입니다.
    @Field("username")
    private String username;

    // 일반적으로 있는 비밀번호입니다.
    @Field("password")
    private String password;

    // role 조작 로그인에 필요한 role 입니다.
    // 필요하지 않으면 사용하지 않아도 됩니다.
    @Field("role")
    private String role;

    // 무차별 공격 로그인 방어용으로 만든 로그인 시도 count 입니다.
    // 필요하지 않으면 사용하지 않아도 됩니다.
    @Field("login_attempt_count")
    private int loginAttemptCount;
}
