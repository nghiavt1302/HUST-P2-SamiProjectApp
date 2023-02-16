package nghiavt.hustp2samiprojectapp.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import nghiavt.hustp2samiprojectapp.constant.AssignStatusEnum;
import nghiavt.hustp2samiprojectapp.constant.ProjectTypeEnum;

@Entity
@Table(name = "assignment")
@Data
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assign_id")
    private Integer assignId;
    @Column(name = "app_id")
    private String appId;
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "project_type")
    private ProjectTypeEnum prjType;
    @Column
    private String term;
    @Column(name = "instructor_id")
    private String instructorId;
    @Column(name = "reviewer_id")
    private String reviewerId;
    @Column(name = "comm_id")
    private String commId;
    @Column(name = "project_id")
    private String projectId;
    @Enumerated(EnumType.STRING)
    private AssignStatusEnum status;

}
