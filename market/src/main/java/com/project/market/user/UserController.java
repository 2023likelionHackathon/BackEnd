package com.project.market.user;

import com.project.market.user.dto.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    private UserService userService;

    @GetMapping("/view/{id}")
    public ResponseEntity view(@PathVariable("id") Long id) {
        UserResponse user = userService.viewUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
