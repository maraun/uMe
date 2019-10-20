package kz.u.u.uMe.models.entities;

import kz.u.u.uMe.models.audits.AuditModel;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "umembers")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(   name="membertype", discriminatorType = DiscriminatorType.STRING)
public class Umember extends AuditModel {

    @Column(name = "parent_id")
    private Long parent_id;

}
