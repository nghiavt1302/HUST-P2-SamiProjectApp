package nghiavt.hustp2samiprojectapp.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import nghiavt.hustp2samiprojectapp.constant.DepartmentEnum;

@Entity
@Table(name = "teacher")
@Data
public class Teacher {
    @Id
    @Column(name = "teacher_id")
    private String teacherId;
    @Column(name = "full_name")
    private String fullName;

    private String phone;
    @Enumerated(EnumType.STRING)
    private DepartmentEnum department;

    private String expertise;

    private Integer quota;

    private String email;
}
