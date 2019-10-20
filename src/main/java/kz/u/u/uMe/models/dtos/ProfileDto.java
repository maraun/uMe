package kz.u.u.uMe.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import kz.u.u.uMe.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
public class ProfileDto extends BaseDto {

    @ApiModelProperty(notes = "Имя", readOnly = true)
    private String firstname;

    @ApiModelProperty(notes = "Фамилия", readOnly = true)
    private String lastname;

    @ApiModelProperty(notes = "Отчество", readOnly = true)
    private String middlename;

    @ApiModelProperty(notes = "Фото", readOnly = true)
    private String photopath;

    @ApiModelProperty(notes = "Телефон", readOnly = true)
    private String phone;

    @ApiModelProperty(notes = "Позиция", readOnly = true)
    private Set<PositionDto> positions;

}