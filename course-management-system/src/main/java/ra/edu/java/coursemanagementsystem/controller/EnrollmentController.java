package ra.edu.java.coursemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.java.coursemanagementsystem.model.dto.request.EnrollCourseRequest;
import ra.edu.java.coursemanagementsystem.model.dto.response.ApiResponse;
import ra.edu.java.coursemanagementsystem.model.dto.response.EnrollmentDetail;
import ra.edu.java.coursemanagementsystem.model.entity.Enrollment;
import ra.edu.java.coursemanagementsystem.service.EnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAllEnrollments(){
        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Get all enrollments successfully!",
                enrollmentService.getAllEnrollments(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getEnrollmentById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Get enrollment " + id + " successfully!",
                    enrollmentService.getEnrollmentById(id),
                    HttpStatus.OK
            ), HttpStatus.OK);
        }
        catch(RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null,
                    HttpStatus.NOT_FOUND
            ), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> createEnrollment(@RequestBody Enrollment enrollment){
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Create enrollment successfully!",
                    enrollmentService.createEnrollment(enrollment),
                    HttpStatus.CREATED
            ), HttpStatus.CREATED);
        }
        catch(RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null,
                    HttpStatus.BAD_REQUEST
            ), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment){
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Update enrollment " + id + " successfully!",
                    enrollmentService.updateEnrollment(id, enrollment),
                    HttpStatus.OK
            ), HttpStatus.OK);
        }
        catch(RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null,
                    HttpStatus.NOT_FOUND
            ), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> deleteEnrollment(@PathVariable Long id){
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Delete enrollment " + id + " successfully!",
                    null,
                    HttpStatus.NO_CONTENT
            ), HttpStatus.NO_CONTENT);
        }
        catch(RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null,
                    HttpStatus.NOT_FOUND
            ), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/enroll-course")
    public ResponseEntity<ApiResponse<EnrollmentDetail>> enrollCourse(@RequestBody EnrollCourseRequest request) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Enroll course successfully!",
                    enrollmentService.enrollCourse(request),
                    HttpStatus.OK
            ), HttpStatus.OK);
        }
        catch(RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null,
                    HttpStatus.BAD_REQUEST
            ), HttpStatus.BAD_REQUEST);
        }
    }
}
