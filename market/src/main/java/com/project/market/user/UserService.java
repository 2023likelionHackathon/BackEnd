package com.project.market.user;

import com.project.market.user.dto.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
//    public User findUser(Long sessionUser) {
//        User user = userRepository.findByEmail(sessionUser.getEmail())
//                .orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 사용자입니다."));
//        return user;
//    }

    public UserResponse viewUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 사용자입니다."));
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getName())
                .picture(user.getPicture())
                .role(user.getRole().getTitle())
                .build();
    }
}
