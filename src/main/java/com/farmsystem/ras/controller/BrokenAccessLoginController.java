package com.farmsystem.ras.controller;

import com.farmsystem.ras.dto.LoginResponse;
import com.farmsystem.ras.dto.RoleLoginDTO;
import com.farmsystem.ras.service.BrokenAccessLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BrokenAccessLoginController {

    private final BrokenAccessLoginService brokenAccessLoginService;

    @PostMapping("/vulnerable-broken-access-login")
    public LoginResponse vulnerableBrokenAccessLogin(@RequestBody RoleLoginDTO roleLoginDTO) {
        return brokenAccessLoginService.vulnerableBrokenAccessLogin(roleLoginDTO);
    }

    @PostMapping("/protected-broken-access-login")
    public LoginResponse protectedBrokenAccessLogin(@RequestBody RoleLoginDTO roleLoginDTO) {
        return brokenAccessLoginService.protectedBrokenAccessLogin(roleLoginDTO);
    }
}
