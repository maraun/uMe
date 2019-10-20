package kz.u.u.uMe.models.entities;

import kz.u.u.uMe.models.audits.AuditModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


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

    @ManyToOne
    @NotNull(message = "role is required")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Role role;

}
