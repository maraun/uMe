package kz.u.u.uMe.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.u.u.uMe.controllers.BaseController;
import kz.u.u.uMe.exceptions.ServiceException;
import kz.u.u.uMe.models.dtos.PositionDto;
import kz.u.u.uMe.models.entities.Position;
import kz.u.u.uMe.models.mappers.PositionMapper;
import kz.u.u.uMe.services.PositionService;
import kz.u.u.uMe.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/positions")
public class PositionController extends BaseController {

     private PositionService positionService;
     private PositionMapper positionMapper;

     @Autowired
     public PositionController(PositionService positionService, PositionMapper positionMapper) {
          this.positionService = positionService;
          this.positionMapper = positionMapper;
     }

     @GetMapping
     @ApiOperation("Получение всех позиции")
     public ResponseEntity<?> getAll(){
          return buildResponse(positionMapper.toDtoList(positionService.findAll()), HttpStatus.OK);
     }


     @GetMapping("{id}")
     @ApiOperation("Получение позиции по идентификатору")
     public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
          return buildResponse(positionMapper.toDto(positionService.findById(id)), HttpStatus.OK);
     }

     @PostMapping
     @ApiOperation("Добавление позиции")
     public ResponseEntity<?> add(@ApiParam("Элемент") @RequestBody PositionDto positionDto) throws ServiceException{
          Position position = positionMapper.toEntity(positionDto);
          position = positionService.save(position);
          return buildResponse(positionMapper.toDto(position),HttpStatus.OK);
     }

     @DeleteMapping
     @ApiOperation("Удаление позиции")
     public ResponseEntity<?> delete (@ApiParam("Элемент") @RequestBody PositionDto positionDto) throws ServiceException{
          positionService.deleteById((positionMapper.toEntity(positionDto)).getId());
          return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
     }

     @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
     @ApiOperation("Изменение позиции")
     public ResponseEntity<?> update(@ApiParam("Элемент") @RequestBody PositionDto positionDto) throws ServiceException {
          Position position = positionService.update(positionMapper.toEntity(positionDto));
          return buildResponse(SuccessResponse.builder()
                  .message("updated")
                  .data(positionMapper.toDto(position))
                  .build(), HttpStatus.OK);
     }



}