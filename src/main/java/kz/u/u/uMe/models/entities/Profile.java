package kz.u.u.uMe.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Profile")
public class Profile extends Umember {

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="middlename")
    private String middlename;

    @Column(name = "photopath")
    private String photopath;

    @Column(name="phone")
    private String phone;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "users_positions",
            joinColumns =
                    {
                            @JoinColumn(
                                    name = "user_id",
                                    nullable = false,
                                    foreignKey = @ForeignKey(name = "fk_users_roles_users")
                            )
                    },
            inverseJoinColumns =
                    {
                            @JoinColumn(
                                    name = "position_id",
                                    nullable = false,
                                    foreignKey = @ForeignKey(name = "fk_users_roles_roles")
                            )
                    }
    )
    private Set<Position> positions;

}
