package nghiavt.hustp2samiprojectapp.model.dataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nghiavt.hustp2samiprojectapp.constant.AssignStatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlainAssignResult {
    private String assignId;
    private Integer studentId;
    private String studentName;
    private String instructorName;
    private String reviewerName;
    private String commNumber;
    private AssignStatusEnum status;
    private String term;

}
