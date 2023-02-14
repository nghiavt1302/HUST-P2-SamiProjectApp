package nghiavt.hustp2samiprojectapp.model.dataObject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT teacher_id, full_name as fullname FROM teacher")
@Data
public class TeachersNameList {
    @Id
    private String teacherId;
    private String fullname;
}
