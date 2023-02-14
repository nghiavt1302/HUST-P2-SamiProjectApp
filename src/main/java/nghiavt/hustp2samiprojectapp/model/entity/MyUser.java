package nghiavt.hustp2samiprojectapp.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import nghiavt.hustp2samiprojectapp.constant.RoleEnum;

@Entity
@Table(name = "user")
@Data
public class MyUser {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String email;

    private String password;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private Boolean status;
}
