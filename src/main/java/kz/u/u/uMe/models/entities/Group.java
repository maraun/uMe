package kz.u.u.uMe.models.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Group")
public class Group extends Umember {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(
            name = "grouptype_id",
            foreignKey = @ForeignKey(name = "fk_groups_grouptypes")
    )
    private GroupType groupType;

    @Column(name = "group_name")
    private String name;

}
