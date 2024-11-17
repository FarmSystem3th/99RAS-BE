package com.farmsystem.ras.service;

import com.farmsystem.ras.dto.LoginResponse;
import com.farmsystem.ras.dto.RoleLoginDTO;
import com.farmsystem.ras.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrokenAccessLoginService {

    private final UserRepository userRepository;

    // 잘못된 접근 제어에 취약한 로그인을 구현하면 됩니다.
    public LoginResponse vulnerableBrokenAccessLogin(RoleLoginDTO roleLoginDTO) {
        return null;
    }

    // 잘못된 접근 제어를 방어하는 로그인을 구현하면 됩니다.
    public LoginResponse protectedBrokenAccessLogin(RoleLoginDTO roleLoginDTO) {
        return null;
    }
}
