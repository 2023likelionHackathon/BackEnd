package com.project.market.user;

import com.project.market.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
//    public User findUser(Long sessionUser) {
//        User user = userRepository.findByEmail(sessionUser.getEmail())
//                .orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 사용자입니다."));
//        return user;
//    }

    public UserDTO.Response viewUser(Long userId) {
        User user = userRepository.findUserWithBoards(userId);

        return user.toDTO();
    }

    public String register(UserDTO.Request req, String imgUrl) {
        log.info("req =>{}", req.getUserId());
        if(req.getRole().equals("USER")){
            userRepository.save(req.toEntity(imgUrl, Role.USER));
        }else if(req.getRole().equals("MERCHANT")){
            userRepository.save(req.toEntity(imgUrl, Role.MERCHANT));
        }
        return "SUCCESS";
    }
}
