package nghiavt.hustp2samiprojectapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import nghiavt.hustp2samiprojectapp.constant.CommitteeRoleEnum;

@Entity
@Table(name = "teacherInComm")
@Data
public class TeacherInComm {
    @Id
    private Integer id;
    private String teacherId;
    private String commId;
    private CommitteeRoleEnum Role;

}
