package com.farmsystem.ras.controller;

import com.farmsystem.ras.dto.JoinDTO;
import com.farmsystem.ras.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public void join(@RequestBody JoinDTO joinDTO) {
        joinService.join(joinDTO);
    }
}
