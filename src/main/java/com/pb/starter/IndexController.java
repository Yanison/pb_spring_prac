package com.pb.starter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final IndexMapper indexMapper;
    @GetMapping("/")
    public String index() {
        int cnt = indexMapper.selectCount();
        log.info("index count: {}", cnt);
        return "index";
    }
}
