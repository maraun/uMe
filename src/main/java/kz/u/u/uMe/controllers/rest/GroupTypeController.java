package kz.u.u.uMe.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.u.u.uMe.controllers.BaseController;
import kz.u.u.uMe.exceptions.ServiceException;
import kz.u.u.uMe.models.dtos.GroupTypeDto;
import kz.u.u.uMe.models.entities.GroupType;
import kz.u.u.uMe.models.mappers.GroupTypeMapper;
import kz.u.u.uMe.services.GroupTypeService;
import kz.u.u.uMe.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groupTypes")
public class GroupTypeController extends BaseController {

     private GroupTypeService groupTypeService;
     private GroupTypeMapper groupTypeMapper;

     @Autowired
     public GroupTypeController(GroupTypeService groupTypeService, GroupTypeMapper groupTypeMapper) {
          this.groupTypeService = groupTypeService;
          this.groupTypeMapper = groupTypeMapper;
     }

     @GetMapping
     @ApiOperation("Получение всех тип груп")
     public ResponseEntity<?> getAll(){
          return buildResponse(groupTypeMapper.toDtoList(groupTypeService.findAll()), HttpStatus.OK);
     }


     @GetMapping("{id}")
     @ApiOperation("Получение типа группы по идентификатору")
     public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
          return buildResponse(groupTypeMapper.toDto(groupTypeService.findById(id)), HttpStatus.OK);
     }

     @PostMapping
     @ApiOperation("Добавление типа группы")
     public ResponseEntity<?> add(@ApiParam("Элемент") @RequestBody GroupTypeDto groupTypeDto) throws ServiceException{
          GroupType groupType = groupTypeMapper.toEntity(groupTypeDto);
          groupType = groupTypeService.save(groupType);
          return buildResponse(groupTypeMapper.toDto(groupType),HttpStatus.OK);
     }

     @DeleteMapping
     @ApiOperation("Удаление типа группы")
     public ResponseEntity<?> delete (@ApiParam("Элемент") @RequestBody GroupTypeDto groupTypeDto) throws ServiceException{
          groupTypeService.deleteById((groupTypeMapper.toEntity(groupTypeDto)).getId());
          return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
     }

     @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
     @ApiOperation("Изменение типа группы")
     public ResponseEntity<?> update(@ApiParam("Элемент") @RequestBody GroupTypeDto groupTypeDto) throws ServiceException {
          GroupType groupType = groupTypeService.update(groupTypeMapper.toEntity(groupTypeDto));
          return buildResponse(SuccessResponse.builder()
                  .message("updated")
                  .data(groupTypeMapper.toDto(groupType))
                  .build(), HttpStatus.OK);
     }



}
