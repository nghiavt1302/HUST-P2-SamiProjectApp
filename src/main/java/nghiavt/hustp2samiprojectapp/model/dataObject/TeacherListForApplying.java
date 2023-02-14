package nghiavt.hustp2samiprojectapp.model.dataObject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT teacher_id, full_name as fullname, department, expertise FROM teacher")
@Data
public class TeacherListForApplying {
    @Id
    private String teacherId;
    private String fullname;
    private String department;
    private String expertise;
}
