package com.project.market.service;

import com.project.market.domain.Store;
import com.project.market.dto.UserDTO;
import com.project.market.exception.AlreadyExistsException;
import com.project.market.exception.NonExistentStoreException;
import com.project.market.exception.NonExistentUserException;
import com.project.market.repository.StoreRepository;
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
    private final StoreRepository storeRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO.Profile viewUser(Long userId) {
        User user = userRepository.findUserWithBoards(userId)
                .orElseThrow(()-> new NonExistentUserException());

        return user.toProfileDto();
    }

    public String register(UserDTO.Request req) {

        Optional<User> optionalUser = userRepository.findByUserId(req.getUserId());
        if(optionalUser.isPresent()){
            throw new AlreadyExistsException("이미 존재하는 아이디 입니다.");
        }
        String pass = passwordEncoder.encode(req.getPw());
        if(req.getRole().equals("USER")){
            userRepository.save(req.toEntity("https://markeybucket.s3.ap-northeast-2.amazonaws.com/%ED%94%84%EB%A1%9C%ED%95%84%EC%82%AC%EC%A7%84/%EC%9D%BC%EB%B0%98%EC%82%AC%EC%9A%A9%EC%9E%90.png", Role.USER, pass));
        }else if(req.getRole().equals("MERCHANT")){
            User user = userRepository.save(req.toEntity("https://markeybucket.s3.ap-northeast-2.amazonaws.com/%ED%94%84%EB%A1%9C%ED%95%84%EC%82%AC%EC%A7%84/%EC%82%AC%EC%9E%A5%EB%8B%98.png", Role.MERCHANT, pass));
            Store store = storeRepository.findByCode(req.getCode())
                    .orElseThrow(()-> new NonExistentStoreException());
            storeRepository.save(store.setMerchant(user));
        }
        return "SUCCESS";
    }

    @Transactional(readOnly = true)
    public String checkUserIdDuplicaton(String userId){
        boolean useridDuplication = userRepository.existsByUserId(userId);
        if(useridDuplication){
            throw new AlreadyExistsException("이미 존재하는 아이디입니다.");
        }
        return "SUCCESS";
    }
    @Transactional(readOnly = true)
    public String checkNicknameDuplicaton(String nickname){
        boolean nicknameDuplication = userRepository.existsByNickname(nickname);
        if(nicknameDuplication){
            throw new AlreadyExistsException("이미 존재하는 닉네임입니다.");
        }
        return "SUCCESS";
    }
    @Transactional(readOnly = true)
    public String checkEmailDuplicaton(String email){
        boolean emailDuplication = userRepository.existsByEmail(email);
        if(emailDuplication){
            throw new AlreadyExistsException("이미 존재하는 이메일입니다.");
        }
        return "SUCCESS";
    }
}
