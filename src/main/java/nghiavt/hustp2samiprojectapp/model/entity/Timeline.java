package nghiavt.hustp2samiprojectapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "timeline")
@Data
public class Timeline {
    @Id
    private String id;
    private String event;
    private Timestamp expire;
}
