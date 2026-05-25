package ra.edu.java.coursemanagementsystem.repository.impl;

import org.springframework.stereotype.Repository;
import ra.edu.java.coursemanagementsystem.model.entity.Enrollment;
import ra.edu.java.coursemanagementsystem.repository.EnrollmentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnrollmentRepoImpl implements EnrollmentRepo {
    private final List<Enrollment> enrollments = new ArrayList<>();
    public EnrollmentRepoImpl() {
        enrollments.add(new Enrollment(1001L, "Nguyen Duc Hong Quan", 101L));
        enrollments.add(new Enrollment(1002L, "Le Van C", 103L));
    }

    @Override
    public List<Enrollment> findAll() {
        return enrollments;
    }

    @Override
    public Optional<Enrollment> findById(Long id) {
        return enrollments.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public Enrollment create(Enrollment enrollment) {
        if (findById(enrollment.getId()).isPresent()) {
            throw new RuntimeException("Enrollment with id " + enrollment.getId() + " already exists");
        }
        enrollments.add(enrollment);
        return enrollment;
    }

    @Override
    public Enrollment update(Long id, Enrollment enrollment) {
        Enrollment existing = findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment with id " + id + " does not exist"));
        existing.setStudentName(enrollment.getStudentName());
        existing.setCourseId(enrollment.getCourseId());
        return existing;
    }

    @Override
    public void deleteById(Long id) {
        Enrollment existing = findById(id).orElseThrow(() -> new RuntimeException("Enrollment with id " + id + " does not exist"));
        enrollments.remove(existing);
    }
}
