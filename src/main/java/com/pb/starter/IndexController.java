package com.pb.starter;

import com.pb.starter.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final String FRAGMENTS_PATH = "fragments/index";
    private final String FRAGMENT = "index";

    @GetMapping("/layout")
    public String layout(
            @ModelAttribute("user")
            @AuthenticationPrincipal CustomUserDetails user,
            Model model
    ) {
        model.addAttribute("fragPath",FRAGMENTS_PATH);
        model.addAttribute("fragment",FRAGMENT);
        return "layout-after";
    }
}
