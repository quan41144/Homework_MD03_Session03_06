package ra.edu.java.coursemanagementsystem.repository.impl;

import org.springframework.stereotype.Repository;
import ra.edu.java.coursemanagementsystem.model.entity.Instructor;
import ra.edu.java.coursemanagementsystem.repository.InstructorRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstructorRepoImpl implements InstructorRepo {
    private final List<Instructor> instructors = new ArrayList<>();
    public InstructorRepoImpl() {
        instructors.add(new Instructor(1L, "Nguyen Van A", "a.nguyen@email.com"));
        instructors.add(new Instructor(2L, "Tran Thi B", "b.tran@email.com"));
    }

    @Override
    public List<Instructor> findAll() {
        return instructors;
    }

    @Override
    public Optional<Instructor> findById(Long id) {
        return instructors.stream().filter(instructor -> instructor.getId().equals(id)).findFirst();
    }

    @Override
    public Instructor create(Instructor instructor) {
        if (findById(instructor.getId()).isPresent()) {
            throw new RuntimeException("Instructor with id " + instructor.getId() + " already exists");
        }
        instructors.add(instructor);
        return instructor;
    }

    @Override
    public Instructor update(Long id, Instructor instructor) {
        Instructor existing = findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor with id " + id + " does not exist"));
        existing.setName(instructor.getName());
        existing.setEmail(instructor.getEmail());
        return existing;
    }

    @Override
    public void deleteById(Long id) {
        Instructor existing = findById(id).orElseThrow(() -> new RuntimeException("Instructor with id " + id + " does not exist"));
        instructors.remove(existing);
    }
}
