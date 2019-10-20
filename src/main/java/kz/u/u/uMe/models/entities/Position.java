package kz.u.u.uMe.models.entities;

import kz.u.u.uMe.models.audits.AuditModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Position extends AuditModel {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
