package com.farmsystem.ras.service;

import com.farmsystem.ras.domain.User;
import com.farmsystem.ras.dto.LoginResponse;
import com.farmsystem.ras.dto.RoleLoginDTO;
import com.farmsystem.ras.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrokenAccessLoginService {

    private final UserRepository userRepository;

    // 잘못된 접근 제어에 취약한 로그인을 구현하면 됩니다. (role 사용O)
    public LoginResponse vulnerableBrokenAccessLogin(RoleLoginDTO roleLoginDTO) {
        LoginResponse response = new LoginResponse();
        // 입력값 검증
        if (roleLoginDTO.getUsername() != null) {
            // 사용자 조회
            var user = userRepository.findByUsername(roleLoginDTO.getUsername());
            if (user != null) {
                // 비밀번호 평문 또는 SHA-256으로 검증
                String hashedPassword = encryptPassword(roleLoginDTO.getPassword());
                if (user.getPassword().equals(roleLoginDTO.getPassword()) || user.getPassword().equals(hashedPassword)) {
                    // Role은 검증하지 않고 요청된 값을 그대로 반환 (취약점)
                    response.setUsername(roleLoginDTO.getUsername());
                    response.setPassword(roleLoginDTO.getPassword());
                    response.setRole(roleLoginDTO.getRole());
                    response.setLoginStatus("LOGIN SUCCESS");
                    response.setVulnerablePoint("broken access control login");
                } else {
                    // 비밀번호 불일치
                    response.setUsername(roleLoginDTO.getUsername());
                    response.setPassword(roleLoginDTO.getPassword());
                    response.setRole(roleLoginDTO.getRole());
                    response.setLoginStatus("LOGIN FAILED");
                    response.setVulnerablePoint("INVALID PASSWORD");
                }
            } else {
                // 사용자 이름 없음
                response.setUsername(roleLoginDTO.getUsername());
                response.setPassword(roleLoginDTO.getPassword());
                response.setRole(roleLoginDTO.getRole());
                response.setLoginStatus("LOGIN FAILED");
                response.setVulnerablePoint("USER NOT FOUND");
            }
        } else {
            // 입력값 누락
            response.setUsername("입력값을 모두 입력해주세요");
            response.setPassword("입력값을 모두 입력해주세요");
            response.setRole("입력값을 모두 입력해주세요");
            response.setLoginStatus("LOGIN FAILED");
            response.setVulnerablePoint("LOGIN FAILED");
        }

        return response;
    }

    // 잘못된 접근 제어를 방어하는 로그인을 구현
    public LoginResponse protectedBrokenAccessLogin(RoleLoginDTO roleLoginDTO) {
        LoginResponse response = new LoginResponse();
        // 입력값 검증
        if (roleLoginDTO.getUsername() != null) {
            // 사용자 조회
            var user = userRepository.findByUsername(roleLoginDTO.getUsername());
            if (user != null) {
                // 비밀번호 평문 또는 SHA-256으로 검증
                String hashedPassword = encryptPassword(roleLoginDTO.getPassword());
                if (user.getPassword().equals(roleLoginDTO.getPassword()) || user.getPassword().equals(hashedPassword)) {
                    // Role 검증하여 role 조작 방지
                    if (user.getRole().equals(roleLoginDTO.getRole())) {
                        // Role과 비밀번호가 모두 일치
                        response.setUsername(roleLoginDTO.getUsername());
                        response.setPassword(roleLoginDTO.getPassword());
                        response.setRole(roleLoginDTO.getRole());
                        response.setLoginStatus("LOGIN SUCCESS");
                        response.setVulnerablePoint("none"); // 취약점 없음
                    } else {
                        // Role 불일치
                        response.setUsername(roleLoginDTO.getUsername());
                        response.setPassword(roleLoginDTO.getPassword());
                        response.setRole(user.getRole());
                        response.setLoginStatus("LOGIN FAILED");
                        response.setVulnerablePoint("ROLE MISMATCH protected broken access control login");
                    }
                } else {
                    // 비밀번호 불일치
                    response.setUsername(roleLoginDTO.getUsername());
                    response.setPassword(roleLoginDTO.getPassword());
                    response.setRole(roleLoginDTO.getRole());
                    response.setLoginStatus("LOGIN FAILED");
                    response.setVulnerablePoint("INVALID PASSWORD");
                }
            } else {
                // 사용자 이름 없음
                response.setUsername(roleLoginDTO.getUsername());
                response.setPassword(roleLoginDTO.getPassword());
                response.setRole(roleLoginDTO.getRole());
                response.setLoginStatus("LOGIN FAILED");
                response.setVulnerablePoint("USER NOT FOUND");
            }
        } else {
            // 입력값 누락
            response.setUsername("입력값을 모두 입력해주세요");
            response.setPassword("입력값을 모두 입력해주세요");
            response.setRole("입력값을 모두 입력해주세요");
            response.setLoginStatus("LOGIN FAILED");
            response.setVulnerablePoint("LOGIN FAILED");
        }
        return response;
    }

    // 비밀번호 암호화 (SHA-256)
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