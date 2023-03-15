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
    private String email;
    @Enumerated(EnumType.STRING)
    private DepartmentEnum department;
    private String phone;
    private String expertise;
    private float param;
    private Integer assigned;
    private Integer quota;
}
