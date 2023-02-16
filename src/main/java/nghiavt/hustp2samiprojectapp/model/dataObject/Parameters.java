package nghiavt.hustp2samiprojectapp.model.dataObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "parameters")
@Data
public class Parameters {
    @Id
    private String term;
    @Column(name = "bmtcb_param")
    private String bmtcbParam;
    @Column(name = "bmtt_param")
    private String bmttParam;
    @Column(name = "bmtud_param")
    private String bmtudParam;
    private Boolean current;
}
