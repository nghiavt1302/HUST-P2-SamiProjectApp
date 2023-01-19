package nghiavt.hustp2samiprojectapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "committee")
@Data
public class Committee {
    @Id
    @Column(name = "comm_id")
    private String commId;

    private String president;

    private String secretary;

    private String member;
}
