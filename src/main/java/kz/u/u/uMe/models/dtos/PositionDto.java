package kz.u.u.uMe.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import kz.u.u.uMe.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PositionDto extends BaseDto {

    @ApiModelProperty(notes = "Наименование", readOnly = true)
    private String name;

}