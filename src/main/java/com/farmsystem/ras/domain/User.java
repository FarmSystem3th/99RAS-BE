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
public class User {
    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Field("role")
    private String role;

    @Field("login_attempt_count")
    private int loginAttemptCount;
}
