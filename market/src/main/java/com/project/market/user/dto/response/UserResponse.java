package com.project.market.user.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String userId;
    private String userPw;
    private String userName;
    private String picture;
    private String role;
}
