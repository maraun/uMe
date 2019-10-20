package kz.u.u.uMe.models.entities;

import kz.u.u.uMe.models.audits.AuditModel;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupType extends AuditModel {//department,branch,division etc.

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
