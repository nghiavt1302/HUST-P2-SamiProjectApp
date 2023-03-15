package nghiavt.hustp2samiprojectapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "timeline")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timeline {
    @Id
    private String id;
    private String event;
    private Timestamp expire;
}
