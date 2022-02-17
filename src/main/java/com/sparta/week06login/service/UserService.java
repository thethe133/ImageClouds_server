package com.sparta.week06login.service;

import com.sparta.week06login.dto.SignupRequestDto;
import com.sparta.week06login.dto.SignupResponseDto;
import com.sparta.week06login.model.User;
import com.sparta.week06login.repository.UserRepository;
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
        Optional<User> foundUsername = userRepository.findByUsername(username);
        if (foundUsername.isPresent()) {
            throw new IllegalArgumentException("사용자 ID가 이미 존재합니다.");
        }

        // 회원 닉네임 중복 확인
        Optional<User> foundUserNickname = userRepository.findByNickname(requestDto.getNickname());
        if (foundUserNickname.isPresent()) {
            throw new IllegalArgumentException("사용자 닉네임이 이미 존재합니다.");
        }

        //회원 이메일 중복확인
        Optional<User> foundUserEmail = userRepository.findByEmail(requestDto.getEmail());
        if (foundUserEmail.isPresent()) {
            throw new IllegalArgumentException("사용자 이메일이 이미 존재합니다.");
        }

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