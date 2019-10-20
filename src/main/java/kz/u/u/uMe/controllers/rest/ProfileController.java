package kz.u.u.uMe.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.u.u.uMe.controllers.BaseController;
import kz.u.u.uMe.exceptions.ServiceException;
import kz.u.u.uMe.models.dtos.ProfileDto;
import kz.u.u.uMe.models.entities.Profile;
import kz.u.u.uMe.models.mappers.ProfileMapper;
import kz.u.u.uMe.services.ProfileService;
import kz.u.u.uMe.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController extends BaseController {

     private ProfileService profileService;
     private ProfileMapper profileMapper;

     @Autowired
     public ProfileController(ProfileService profileService, ProfileMapper profileMapper) {
          this.profileService = profileService;
          this.profileMapper = profileMapper;
     }

     @GetMapping
     @ApiOperation("Получение всех профилей")
     public ResponseEntity<?> getAll(){
          return buildResponse(profileMapper.toDtoList(profileService.findAll()), HttpStatus.OK);
     }


     @GetMapping("{id}")
     @ApiOperation("Получение профиля по идентификатору")
     public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
          return buildResponse(profileMapper.toDto(profileService.findById(id)), HttpStatus.OK);
     }

     @PostMapping
     @ApiOperation("Добавление профиля")
     public ResponseEntity<?> add(@ApiParam("Элемент") @RequestBody ProfileDto profileDto) throws ServiceException{
          Profile profile = profileMapper.toEntity(profileDto);
          profile = profileService.save(profile);
          return buildResponse(profileMapper.toDto(profile),HttpStatus.OK);
     }

     @DeleteMapping
     @ApiOperation("Удаление профиля")
     public ResponseEntity<?> delete (@ApiParam("Элемент") @RequestBody ProfileDto profileDto) throws ServiceException{
          profileService.deleteById((profileMapper.toEntity(profileDto)).getId());
          return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
     }

     @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
     @ApiOperation("Изменение профиля")
     public ResponseEntity<?> update(@ApiParam("Элемент") @RequestBody ProfileDto profileDto) throws ServiceException {
          Profile profile = profileService.update(profileMapper.toEntity(profileDto));
          return buildResponse(SuccessResponse.builder()
                  .message("updated")
                  .data(profileMapper.toDto(profile))
                  .build(), HttpStatus.OK);
     }



}