package com.pb.starter.subject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectRestController {

    @RequestMapping("/list")
    public ResponseEntity<?> getSubjectList() {
        return ResponseEntity.ok("Subject List");
    }
}
