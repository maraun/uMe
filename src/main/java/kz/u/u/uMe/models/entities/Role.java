package kz.u.u.uMe.models.entities;

import kz.u.u.uMe.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AuditModel {
/*
    public final static Long ROLE_TEACHER_ID = 1L;
    public final static Long ROLE_STUDENT_ID = 4L;

    public final static String ROLE_TEACHER_NAME = "ROLE_TEACHER";
    public final static String ROLE_STUDENT_NAME = "ROLE_STUDENT";*/

    @Column(unique = true)
    private String name;
}
