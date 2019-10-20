package kz.u.u.uMe.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.u.u.uMe.controllers.BaseController;
import kz.u.u.uMe.exceptions.ServiceException;
import kz.u.u.uMe.models.mappers.RoleMapper;
import kz.u.u.uMe.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends BaseController {

     private RoleService roleService;
     private RoleMapper roleMapper;

     @Autowired
     public RoleController(RoleService roleService, RoleMapper roleMapper) {
          this.roleService = roleService;
          this.roleMapper = roleMapper;
     }

     @GetMapping
     public ResponseEntity<?> getAll(){
          return buildResponse(roleMapper.toDtoList(roleService.findAll()), HttpStatus.OK);
     }

     @GetMapping("{id}")
     @ApiOperation("Получение всех ролей в грязном виде")
     public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
          return buildResponse(roleMapper.toDto(roleService.findById(id)), HttpStatus.OK);
     }

}
