package com.farmsystem.ras.service;

import com.farmsystem.ras.domain.User;
import com.farmsystem.ras.dto.JoinDTO;
import com.farmsystem.ras.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;

    public void join(JoinDTO joinDTO) {
        User user = User.builder()
                .username(joinDTO.getUsername())
                .password(joinDTO.getPassword())
                .role("USER")
                .loginAttemptCount(0)
                .build();

        userRepository.save(user);
    }
}
