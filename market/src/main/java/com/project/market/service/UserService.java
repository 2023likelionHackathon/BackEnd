package com.project.market.service;

import com.project.market.dto.UserDTO;
import com.project.market.exception.AlreadyExistsException;
import com.project.market.exception.NonExistentUserException;
import com.project.market.repository.UserRepository;
import com.project.market.domain.Role;
import com.project.market.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO.Profile viewUser(Long userId) {
        User user = userRepository.findUserWithBoards(userId)
                .orElseThrow(()-> new NonExistentUserException());

        return user.toProfileDto();
    }

    public String register(UserDTO.Request req, String imgUrl) {

        Optional<User> optionalUser = userRepository.findByUserId(req.getUserId());
        if(optionalUser.isPresent()){
            throw new AlreadyExistsException("이미 존재하는 아이디 입니다.");
        }
        String pass = passwordEncoder.encode(req.getPw());
        if(req.getRole().equals("USER")){
            userRepository.save(req.toEntity(imgUrl, Role.USER, pass));
        }else if(req.getRole().equals("MERCHANT")){
            userRepository.save(req.toEntity(imgUrl, Role.MERCHANT, pass));
        }
        return "SUCCESS";
    }
}
