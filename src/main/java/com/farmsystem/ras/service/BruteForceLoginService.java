package com.farmsystem.ras.service;

import com.farmsystem.ras.domain.User;
import com.farmsystem.ras.dto.LoginDTO;
import com.farmsystem.ras.dto.LoginResponse;
import com.farmsystem.ras.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BruteForceLoginService {

    private final UserRepository userRepository;

    // 무차별 공격에 취약한 로그인을 구현하면 됩니다. (role 사용X)
    public LoginResponse vulnerableBruteForceLogin(LoginDTO loginDTO) {
        //로그인 정보 받기
        LoginResponse response = new LoginResponse();
        response.setUsername(loginDTO.getUsername());
        response.setPassword(loginDTO.getPassword());

        //유저 확인 (ID 확인)
        User user = userRepository.findByUsername(loginDTO.getUsername());

        // 사용자가 존재하지 않는 경우
        if (user == null) {
            response.setLoginStatus("LOGIN FAILED");
            response.setVulnerablePoint("USER NOT FOUND");
            return response;
        }
        // 비밀번호 일치 여부 확인
        if (user.getPassword().equals(loginDTO.getPassword())) {
            response.setLoginStatus("LOGIN SUCCESS");
            response.setVulnerablePoint("BRUTE FORCE ATTACK");
        } else {
            response.setLoginStatus("LOGIN FAILED");
            response.setVulnerablePoint("WRONG PASSWORD - NO LOCKING");
            return response;
        }
        return response;
    }

    // 무차별 공격을 방어하는 로그인을 구현하면 됩니다. (role 사용X)
    public LoginResponse protectedBruteForceLogin(LoginDTO loginDTO) {
        LoginResponse response = new LoginResponse();
        response.setUsername(loginDTO.getUsername());
        response.setPassword(loginDTO.getPassword());

        // 사용자 조회
        User user = userRepository.findByUsername(loginDTO.getUsername());

        // 사용자가 존재하지 않는 경우
        if (user == null) {
            response.setLoginStatus("LOGIN FAILED");
            response.setVulnerablePoint("USER NOT FOUND");
            return response;
        }

        // 계정이 잠긴 경우
        if (user.isLacked()) {
            response.setLoginStatus("LOGIN FAILED");
            response.setVulnerablePoint("ACCOUNT LOCKED");
            return response;
        }

        // ID는 일치하지만 패스워드는 일치하지 않는 경우 로그인 시도 횟수를 증가
        if (user.getUsername().equals(loginDTO.getUsername()) &&
                !user.getPassword().equals(loginDTO.getPassword())) {
            user.setLoginAttemptCount(user.getLoginAttemptCount() + 1);

            // 5회 이상 실패 시 계정 잠금 처리
            if (user.getLoginAttemptCount() >= 5) {
                user.setLacked(true);
                userRepository.save(user); // DB 업데이트
                response.setLoginStatus("LOGIN FAILED");
                response.setVulnerablePoint("ACCOUNT LOCKED");
                return response;
            }

            // 로그인 실패 시도 횟수 누적
            userRepository.save(user);
            response.setLoginStatus("LOGIN FAILED");
            response.setVulnerablePoint("WRONG PASSWORD");
            return response;
        }
        // 로그인 성공 시
        else if (user.getUsername().equals(loginDTO.getUsername())) {
            user.setLoginAttemptCount(0); // 로그인 시도 횟수 초기화
            userRepository.save(user);
            response.setLoginStatus("LOGIN SUCCESS");
            response.setVulnerablePoint("NONE");
            return response;
        }


        // 기본 로그인 실패 처리
        response.setLoginStatus("LOGIN FAILED");
        response.setVulnerablePoint("INVALID CREDENTIALS");
        return response;
    }

    // 5회 이상 로그인 실패한 계정에 대해 로그인 시도 횟수 초기화
    public void resetLoginAttemptCount(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        user.setLacked(false);
        user.setLoginAttemptCount(0);
        userRepository.save(user);
    }
}


