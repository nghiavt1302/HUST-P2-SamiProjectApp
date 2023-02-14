package nghiavt.hustp2samiprojectapp.model.dataObject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "term")
@Data
public class Term {
    @Id
    private String term;
    private Boolean current;
}
