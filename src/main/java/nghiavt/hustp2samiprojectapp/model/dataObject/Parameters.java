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
    private Float bmtcbParam;
    @Column(name = "bmtt_param")
    private Float bmttParam;
    @Column(name = "bmtud_param")
    private Float bmtudParam;
    private Boolean current;
}
