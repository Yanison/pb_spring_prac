package com.pb.starter.subject;

import com.pb.starter.model.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/subject")
public class SubjectController {

    private final String FRAGMENTS_PATH = "fragments/subject/app-subject";
    private final String FRAGMENT = "app-subject";

    @GetMapping("/main")
    @PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String subject(Model model, @AuthenticationPrincipal CustomUserDetails user) {
        model.addAttribute("user",user.toUserEntity());
        model.addAttribute("fragPath",FRAGMENTS_PATH);
        model.addAttribute("fragment",FRAGMENT);
        return "layout-after";
    }
}
