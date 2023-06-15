package nghiavt.hustp2samiprojectapp.model.testing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherAssignments {
    private String assignId;
    private String app_id;
    private String comm_id;
    private String instructor_id;
    private String projectType;
    private String projectId;
    private String reviewerId;
    private String status;
    private Integer studentId;
    private String term;

    public TeacherAssignments(String assignId, String instructor_id, String projectType, String projectId, String status, Integer studentId, String term) {
        this.assignId = assignId;
        this.instructor_id = instructor_id;
        this.projectType = projectType;
        this.projectId = projectId;
        this.status = status;
        this.studentId = studentId;
        this.term = term;
    }
}
