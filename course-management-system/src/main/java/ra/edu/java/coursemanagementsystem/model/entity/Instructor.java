package ra.edu.java.coursemanagementsystem.model.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Instructor {
    private Long id;
    private String name;
    private String email;
}
