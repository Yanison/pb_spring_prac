package com.pb.starter.subject;

import com.pb.starter.model.UserEntity;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/subject")
public class SubjectController {

    @GetMapping("/main")
    public String subject(Model model, HttpSession session) {
        UserEntity u = (UserEntity) session.getAttribute("user");
        model.addAttribute("user", u);
        return "/subject/app-notes";
    }
}
