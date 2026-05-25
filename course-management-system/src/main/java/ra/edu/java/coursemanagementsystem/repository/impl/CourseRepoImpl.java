package ra.edu.java.coursemanagementsystem.repository.impl;
import org.springframework.stereotype.Repository;
import ra.edu.java.coursemanagementsystem.model.entity.Course;
import ra.edu.java.coursemanagementsystem.repository.CourseRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepoImpl implements CourseRepo {
    private final List<Course> courses = new ArrayList<>();
    public CourseRepoImpl() {
        courses.add(new Course(101L, "Java BootCamp", "Active", 1L));
        courses.add(new Course(102L, "Web Development", "Pending", 1L));
        courses.add(new Course(103L, "Database Design", "Active", 2L));
    }

    @Override
    public List<Course> findAll() {
        return courses;
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courses.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public Course create(Course course) {
        if (findById(course.getId()).isPresent()) {
            throw new RuntimeException("Course with id " + course.getId() + " already exists");
        }
        courses.add(course);
        return course;
    }

    @Override
    public Course update(Long id, Course course) {
        Course existing = findById(id)
                .orElseThrow(() -> new RuntimeException("Course with id " + id + " not found"));
        existing.setTitle(course.getTitle());
        existing.setStatus(course.getStatus());
        existing.setInstructorId(course.getInstructorId());
        return existing;
    }

    @Override
    public void deleteById(Long id) {
        Course existing = findById(id)
                .orElseThrow(() -> new RuntimeException("Course with id " + id + " not found"));
        courses.remove(existing);
    }
}
