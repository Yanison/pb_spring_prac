package com.pb.starter.subject;

import com.pb.starter.model.CustomUserDetails;
import com.pb.starter.model.SubjectEntity;
import com.pb.starter.model.SubjectSearchParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.pb.starter.component.constant.Constant.BASIC_STRATEGY;

@Slf4j
@Controller
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectServiceContext ssc;

    private final String FRAGMENTS_PATH = "fragments/subject/app-subject";
    private final String FRAGMENT = "app-subject";
    @GetMapping("/main")
    @PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String subject(
            Model model,
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            @ModelAttribute("searchParam") SubjectSearchParam searchParam
    ) {
        model.addAttribute("user",user.toUserEntity());
        model.addAttribute("fragPath",FRAGMENTS_PATH);
        model.addAttribute("fragment",FRAGMENT);

        Pageable pageable = PageRequest.of(page-1,size);
        Page<SubjectEntity> paged = ssc.getService(BASIC_STRATEGY).pagedList(searchParam,pageable);
        model.addAttribute("list",paged.getContent());
        model.addAttribute("pageSize", size);
        model.addAttribute("pageNumber", page);
        return "layout-after";
    }
}
