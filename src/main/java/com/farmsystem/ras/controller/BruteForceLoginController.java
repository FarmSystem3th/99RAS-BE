package com.farmsystem.ras.controller;

import com.farmsystem.ras.dto.LoginDTO;
import com.farmsystem.ras.dto.LoginResponse;
import com.farmsystem.ras.service.BruteForceLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BruteForceLoginController { // 신경쓰지 않아도 됩니다.

    private final BruteForceLoginService bruteForceLoginService;

    @PostMapping("/vulnerable-brute-force-login")
    public LoginResponse vulnerableBruteForceLogin(@RequestBody LoginDTO loginDTO) {
        return bruteForceLoginService.vulnerableBruteForceLogin(loginDTO);
    }

    @PostMapping("/protected-brute-force-login")
    public LoginResponse protectedBruteForceLogin(@RequestBody LoginDTO loginDTO) {
        return bruteForceLoginService.protectedBruteForceLogin(loginDTO);
    }

    @PostMapping("/reset-login-attempt-count")
    public void resetLoginAttemptCount(@RequestBody LoginDTO loginDTO) {
        bruteForceLoginService.resetLoginAttemptCount(loginDTO);
    }

}
