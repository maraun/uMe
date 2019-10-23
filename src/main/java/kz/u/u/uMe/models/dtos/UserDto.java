package kz.u.u.uMe.models.dtos;

import kz.u.u.uMe.models.dtos.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    private ProfileDto profile;

    private String email;

    private String login;

    private String password;

    private Set<RoleDto> roles;

}
