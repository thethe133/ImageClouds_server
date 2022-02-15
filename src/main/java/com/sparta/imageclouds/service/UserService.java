package com.sparta.imageclouds.service;

import com.sparta.imageclouds.dto.SignupRequestDto;
import com.sparta.imageclouds.dto.SignupResponseDto;
import com.sparta.imageclouds.model.User;
import com.sparta.imageclouds.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public SignupResponseDto registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();

        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("사용자 ID가 이미 존재합니다.");
        }

        // 닉네임, 이메일 중복 확인은 여기서 안 해도 될까? 그럼 어디서 되고 있는 거지?

        //패스워드 암호화

        String nickname = requestDto.getNickname();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        User user = new User(username, nickname, password, email);
        userRepository.save(user);

        Optional<User> savedUser = userRepository.findByUsername(username);
        Long uid = savedUser.get().getUid();
        Boolean ok = true;
        SignupResponseDto responseDto = new SignupResponseDto(uid, username, nickname, email, ok);
        return responseDto;
    }
}