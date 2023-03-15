package nghiavt.hustp2samiprojectapp.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import nghiavt.hustp2samiprojectapp.constant.ProjectQualityEnum;
import nghiavt.hustp2samiprojectapp.constant.ProjectTypeEnum;

@Entity
@Table(name = "project")
@Data
public class Project {
    @Id
    @Column(name = "project_id")
    private String projectId;
    private Integer studentId;
    private String title;
    private String link;
    private String plagiarismCheck;
    private String term;
    @Enumerated(EnumType.STRING)
    private ProjectTypeEnum projectType;
    @Enumerated(EnumType.STRING)
    private ProjectQualityEnum status;
    private float instructorGrade;
    private float reviewerGrade;
    private float committeeGrade;
    private float programGrade;
    private String keywords;
}
