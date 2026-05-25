package ra.edu.java.coursemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.java.coursemanagementsystem.model.dto.response.ApiResponse;
import ra.edu.java.coursemanagementsystem.model.entity.Course;
import ra.edu.java.coursemanagementsystem.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(){
        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Get all course successfully!",
                courseService.getAllCourses(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Get course " + id + " successfully!",
                    courseService.getCourseById(id),
                    HttpStatus.OK
            ), HttpStatus.OK);
        }
        catch(RuntimeException e) {
            return new ResponseEntity<>(new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null,
                    HttpStatus.NOT_FOUND
            ), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody Course course){
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Insert course successfully!",
                    courseService.createCourse(course),
                    HttpStatus.CREATED
            ), HttpStatus.CREATED);
        }
        catch(RuntimeException e) {
            return new ResponseEntity<>(new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null,
                    HttpStatus.BAD_REQUEST
            ), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable Long id, @RequestBody Course course){
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Update course " + id + " successfully!",
                    courseService.updateCourse(id, course),
                    HttpStatus.OK
            ), HttpStatus.OK);
        }
        catch(RuntimeException e) {
            return new ResponseEntity<>(new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null,
                    HttpStatus.NOT_FOUND
            ), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourse(@PathVariable Long id){
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    true,
                    "Delete course " + id + " successfully!",
                    null,
                    HttpStatus.NO_CONTENT
            ), HttpStatus.NO_CONTENT);
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
