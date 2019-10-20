package kz.u.u.uMe.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.u.u.uMe.controllers.BaseController;
import kz.u.u.uMe.exceptions.ServiceException;
import kz.u.u.uMe.models.dtos.GroupDto;
import kz.u.u.uMe.models.entities.Group;
import kz.u.u.uMe.models.mappers.GroupMapper;
import kz.u.u.uMe.services.GroupService;
import kz.u.u.uMe.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class GroupController extends BaseController {

     private GroupService groupService;
     private GroupMapper groupMapper;

     @Autowired
     public GroupController(GroupService groupService, GroupMapper groupMapper) {
          this.groupService = groupService;
          this.groupMapper = groupMapper;
     }

     @GetMapping
     @ApiOperation("Получение всех групп")
     public ResponseEntity<?> getAll(){
          return buildResponse(groupMapper.toDtoList(groupService.findAll()), HttpStatus.OK);
     }


     @GetMapping("{id}")
     @ApiOperation("Получение группы по идентификатору")
     public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
          return buildResponse(groupMapper.toDto(groupService.findById(id)), HttpStatus.OK);
     }

     @PostMapping
     @ApiOperation("Добавление группы")
     public ResponseEntity<?> add(@ApiParam("Элемент") @RequestBody GroupDto groupDto) throws ServiceException{
          Group group = groupMapper.toEntity(groupDto);
          group = groupService.save(group);
          return buildResponse(groupMapper.toDto(group),HttpStatus.OK);
     }

     /*@DeleteMapping
     @ApiOperation("Удаление группы")
     public ResponseEntity<?> delete (@ApiParam("Элемент") @RequestBody GroupDto groupDto) throws ServiceException{
          groupService.deleteById((groupMapper.toEntity(groupDto)).getId());
          return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
     }*/
     @DeleteMapping("{id}")
     public ResponseEntity<?> delete (@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException{
          groupService.deleteById(id);
          return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
     }

     @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
     @ApiOperation("Изменение группы")
     public ResponseEntity<?> update(@ApiParam("Элемент") @RequestBody GroupDto groupDto) throws ServiceException {
          Group group = groupService.update(groupMapper.toEntity(groupDto));
          return buildResponse(SuccessResponse.builder()
                  .message("updated")
                  .data(groupMapper.toDto(group))
                  .build(), HttpStatus.OK);
     }



}