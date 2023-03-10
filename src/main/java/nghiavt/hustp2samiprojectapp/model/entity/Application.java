package nghiavt.hustp2samiprojectapp.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import nghiavt.hustp2samiprojectapp.constant.ProjectTypeEnum;

import java.sql.Timestamp;

@Entity
@Table(name = "application")
@Data
public class Application {
    @Id
    @Column(name = "app_id")
    private String appId;
    @Column(name = "project_type")
    @Enumerated(EnumType.STRING)
    private ProjectTypeEnum projectType;

    private String term;

    private String orientation;

    private String opt1;

    private String opt2;

    private String opt3;
    @Column(name = "submit_time")
    private Timestamp submitTime;
    @Column(name = "student_id")
    private Integer studentId;
}
