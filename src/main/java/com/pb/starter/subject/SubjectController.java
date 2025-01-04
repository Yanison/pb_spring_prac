package com.pb.starter.subject;

import com.pb.starter.model.UserEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subject")
public class SubjectController {

//    @GetMapping("/main")
//    public String subject(Model model, HttpSession session) {
//        Object user = session.getAttribute("user");
//        if(user == null){
//            return "redirect:/login";
//        }else{
//            UserEntity ue = (UserEntity) user;
//            model.addAttribute("user", ue);
//        }
//        return "/subject/app-notes";
//    }

    @GetMapping("/main")
    public String subject(Model model, HttpSession session, @AuthenticationPrincipal UserEntity user) {
        model.addAttribute("user", user);
        return "/subject/app-notes";
    }
}
