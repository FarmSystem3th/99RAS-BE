package com.farmsystem.ras.service;

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

    // 무차별 공격에 취약한 로그인을 구현하면 됩니다. (role 사용)
    public LoginResponse vulnerableBruteForceLogin(LoginDTO loginDTO) {
        return null;
    }

    // 무차별 공격을 방어하는 로그인을 구현하면 됩니다. (role 사용)
    public LoginResponse protectedBruteForceLogin(LoginDTO loginDTO) {
        return null;
    }
}
