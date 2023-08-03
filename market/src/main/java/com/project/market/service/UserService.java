package com.project.market.service;

import com.project.market.dto.UserDTO;
import com.project.market.repository.UserRepository;
import com.project.market.domain.Role;
import com.project.market.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
//    public User findUser(Long sessionUser) {
//        User user = userRepository.findByEmail(sessionUser.getEmail())
//                .orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 사용자입니다."));
//        return user;
//    }

    public UserDTO.Profile viewUser(Long userId) {
        User user = userRepository.findUserWithBoards(userId);

        return user.toProfileDto();
    }

    public String register(UserDTO.Request req, String imgUrl) {
        log.info("req =>{}", req.getUserId());
        String pass = passwordEncoder.encode(req.getPw());
        if(req.getRole().equals("USER")){
            userRepository.save(req.toEntity(imgUrl, Role.USER, pass));
        }else if(req.getRole().equals("MERCHANT")){
            userRepository.save(req.toEntity(imgUrl, Role.MERCHANT, pass));
        }
        return "SUCCESS";
    }
}
