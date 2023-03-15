package nghiavt.hustp2samiprojectapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "committee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Committee {
    @Id
    @Column(name = "comm_id")
    private String commId;
    private String term;
    private Integer number;
    private String orient;
}
