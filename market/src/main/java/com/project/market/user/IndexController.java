package com.project.market.user;

import com.project.market.config.oauth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user!=null){
            model.addAttribute("userName", user.getName());
        }
        return "test-oauth2";
    }

    @GetMapping("/main")
    public String main(){

        return "main";
    }
}
