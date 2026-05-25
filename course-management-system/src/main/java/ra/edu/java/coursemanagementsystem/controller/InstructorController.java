package ra.edu.java.coursemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.java.coursemanagementsystem.model.dto.response.ApiResponse;
import ra.edu.java.coursemanagementsystem.model.dto.response.InstructorDetailResponse;
import ra.edu.java.coursemanagementsystem.model.entity.Instructor;
import ra.edu.java.coursemanagementsystem.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final InstructorService instructorService;
    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors(){
        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Get all instructors successfully!",
                instructorService.getAllInstructors(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Get instructor " + id + " successfully!",
                    instructorService.getInstructorById(id),
                    HttpStatus.OK
            ), HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null,
                    HttpStatus.NOT_FOUND
            ), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody Instructor instructor){
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Create instructor successfully!",
                    instructorService.createInstructor(instructor),
                    HttpStatus.CREATED
            ), HttpStatus.CREATED);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null,
                    HttpStatus.BAD_REQUEST
            ), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@PathVariable Long id, @RequestBody Instructor instructor){
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Update instructor " + id + " successfully!",
                    instructorService.updateInstructor(id, instructor),
                    HttpStatus.OK
            ), HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null,
                    HttpStatus.NOT_FOUND
            ), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> deleteInstructor(@PathVariable Long id){
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Delete instructor " + id + " successfully!",
                    null,
                    HttpStatus.NO_CONTENT
            ), HttpStatus.NO_CONTENT);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null,
                    HttpStatus.NOT_FOUND
            ), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/active-courses")
    public ResponseEntity<ApiResponse<List<InstructorDetailResponse>>> getInstructorsWithCourses(){
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Get instructors with active and enrolled courses successfully!",
                    instructorService.getInstructorWithActiveAndEnrolledCourses(),
                    HttpStatus.OK
            ), HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null,
                    HttpStatus.NOT_FOUND
            ), HttpStatus.NOT_FOUND);
        }
    }
}
