package com.pb.starter.subject.restController;

import com.pb.starter.model.CustomUserDetails;
import com.pb.starter.model.SubjectEntity;
import com.pb.starter.model.api.CustomApiResponse;
import com.pb.starter.subject.SubjectService;
import com.pb.starter.subject.SubjectServiceContext;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.pb.starter.model.constant.Constant.*;

@Tag(
        name = "게시글 CRUD",
        description = "게시글 CRUD"
)
@RestController
@RequestMapping("/api/v1/subject")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
public class SubjectRestController {

    private final SubjectServiceContext ssc;

    @GetMapping("/subjects")
    public ResponseEntity getSubjectList(@AuthenticationPrincipal CustomUserDetails user) {
        SubjectService ss = ssc.getService(BASIC_STRATEGY);
        List<SubjectEntity> list = ss.findAll();
        return ResponseEntity.ok(
                CustomApiResponse.builder()
                        .data(list)
                        .status(HttpStatus.OK)
                        .message(SUCCESS)
                        .build()
        );
    }

    @GetMapping("/subject/uuid/{uuid}")
    public ResponseEntity getSubjectByUUID(@AuthenticationPrincipal CustomUserDetails user,@PathVariable(value = "uuid") String uuid) {
        SubjectService ss = ssc.getService(BASIC_STRATEGY);
        Optional<SubjectEntity> subject = ss.findById(uuid);
        boolean ret = subject.isPresent();
        return ResponseEntity.ok(
                CustomApiResponse.builder()
                        .data(ret ? subject.get() : null)
                        .status(ret ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                        .message(ret ? SUCCESS : NOT_FOUND)
                        .build()
        );
    }

    @GetMapping("/subject/id/{id}")
    public ResponseEntity getSubjectById(@AuthenticationPrincipal CustomUserDetails user,@PathVariable(value = "id") String id) {
        SubjectService ss = ssc.getService(NOT_BASIC_STRATEGY);
        Optional<SubjectEntity> subject = ss.findById(id);
        boolean ret = subject.isPresent();
        return ResponseEntity.ok(
                CustomApiResponse.builder()
                        .data(ret ? subject.get() : null)
                        .status(ret ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                        .message(ret ? SUCCESS : NOT_FOUND)
                        .build()
        );
    }

    @PostMapping("/subject/basic")
    public ResponseEntity insert1(@AuthenticationPrincipal CustomUserDetails user, @RequestBody SubjectEntity subjectEntity){
        SubjectService ss = ssc.getService(BASIC_STRATEGY);
        int ret = ss.insert(subjectEntity);
        return ResponseEntity.ok(
                CustomApiResponse.builder()
                        .data(ret > 0)
                        .status(HttpStatus.OK)
                        .message(SUCCESS)
                        .build()
        );
    }

    @PostMapping("/subject/not_basic")
    public ResponseEntity insert2(@AuthenticationPrincipal CustomUserDetails user, @RequestBody SubjectEntity subjectEntity){
        SubjectService ss = ssc.getService(NOT_BASIC_STRATEGY);
        int ret = ss.insert(subjectEntity);
        return ResponseEntity.ok(
                CustomApiResponse.builder()
                        .data(ret > 0)
                        .status(HttpStatus.OK)
                        .message(SUCCESS)
                        .build()
        );
    }

    @PutMapping("/subject")
    public ResponseEntity update(@AuthenticationPrincipal CustomUserDetails user, @RequestBody SubjectEntity subjectEntity){
        SubjectService ss = ssc.getService(BASIC_STRATEGY);
        int ret = ss.update(subjectEntity);
        return ResponseEntity.ok(
                CustomApiResponse.builder()
                        .data(ret > 0)
                        .status(HttpStatus.OK)
                        .message(SUCCESS)
                        .build()
        );
    }

    @DeleteMapping("/subject")
    public ResponseEntity delete(@AuthenticationPrincipal CustomUserDetails user, @RequestBody SubjectEntity subjectEntity){
        SubjectService ss = ssc.getService(BASIC_STRATEGY);
        int ret = ss.update(subjectEntity);
        return ResponseEntity.ok(
                CustomApiResponse.builder()
                        .data(ret > 0)
                        .status(HttpStatus.OK)
                        .message(SUCCESS)
                        .build()
        );
    }
}
