package com.pb.starter.subject;

import com.pb.starter.model.CustomUserDetails;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectRestController {

    @RequestMapping("/list")
    public ResponseEntity<?> getSubjectList(@AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok("Subject List");
    }

    @GetMapping("/hasRoleUser")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> hasRoleUser(@AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok(new Response().toResponse(user, "hasRoleUser"));
    }

    @GetMapping("/hasRoleAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> hasRoleAdmin(@AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok(new Response().toResponse(user, "hasRoleAdmin"));
    }

    @GetMapping("/hasAnyRole")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> hasAnyRole(@AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok(new Response().toResponse(user, "hasAnyRole"));
    }

    @Data
    class Response{
        private String message;
        private String name;
        private String email;
        private String role;

        public Response toResponse(CustomUserDetails user, String message) {
            this.message = message;
            this.name = user.getName();
            this.email = user.getEmail();
            this.role = user.getGrantedAuth();
            return this;
        }
    }


}
