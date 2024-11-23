package com.farmsystem.ras.service;

import com.farmsystem.ras.domain.User;
import com.farmsystem.ras.dto.JoinDTO;
import com.farmsystem.ras.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@RequiredArgsConstructor
public class JoinService { // 신경쓰지 않아도 됩니다.

    private final UserRepository userRepository;

    public JoinDTO join(JoinDTO joinDTO) {
        if (userRepository.existsByUsername(joinDTO.getUsername())) {
            joinDTO.setUsername("이미 존재하는 아이디 입니다.");
            joinDTO.setPassword("회원가입을 다시 시도해주세요.");
            return joinDTO;
        }
        User user = User.builder()
                .username(joinDTO.getUsername())
                .password(joinDTO.getPassword())
                .role("USER")
                .loginAttemptCount(0)
                .build();

        userRepository.save(user);

        return joinDTO;
    }

    public JoinDTO protectedJoin(JoinDTO joinDTO) {
        if (userRepository.existsByUsername(joinDTO.getUsername())) {
            joinDTO.setUsername("이미 존재하는 아이디 입니다.");
            joinDTO.setPassword("회원가입을 다시 시도해주세요.");
            return joinDTO;
        }

        // SHA-256으로 비밀번호 암호화
        String encryptedPassword = encryptPassword(joinDTO.getPassword());

        User user = User.builder()
                .username(joinDTO.getUsername())
                .password(encryptedPassword)
                .role("USER")
                .loginAttemptCount(0)
                .build();

        userRepository.save(user);

        joinDTO.setPassword(encryptedPassword);
        return joinDTO;
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }
}
