package kz.u.u.uMe.models.entities;

import kz.u.u.uMe.models.audits.AuditModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User extends AuditModel {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "email")
    @NotNull(message = "email is required")
    private String email;

    @Column(unique = true, name = "login")
    @NotNull(message = "login is required")
    private String login;

    @NotNull(message = "password is required")
    private String password;

/*    @ManyToOne
    @NotNull(message = "role is required")
    @OnDelete(action = OnDeleteAction.NO_ACTION)*/
@ManyToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
)
@JoinTable(
        name = "users_roless",
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
                                name = "role_id",
                                nullable = false,
                                foreignKey = @ForeignKey(name = "fk_users_roles_roles")
                        )
                }
)
    private Set<Role> roles;

}
