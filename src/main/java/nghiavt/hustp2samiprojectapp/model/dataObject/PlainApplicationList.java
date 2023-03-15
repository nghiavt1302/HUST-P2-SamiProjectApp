package nghiavt.hustp2samiprojectapp.model.dataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlainApplicationList {
    private String appId;
    private Integer studentId;
    private String studentName;
    private float cpa;
    private String orient1;
    private String orient2;
    private String opt1;
    private String opt2;
    private String opt3;
}