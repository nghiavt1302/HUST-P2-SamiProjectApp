package nghiavt.hustp2samiprojectapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "full_name")
    private String fullName;

    private String programme;
    @Column(name = "class_name")
    private String className;

    private Integer classOf;

    private float cpa;

    private String phone;
    @Column(name = "user_id")
    private Integer userId;
}
