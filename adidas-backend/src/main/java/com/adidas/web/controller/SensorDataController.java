package com.adidas.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adidas.service.SensorDataService;

import java.net.URI;

/**
 * @author Error404
 */
@RestController
@RequestMapping("/sensorData")
public class SensorDataController {

    private SensorDataService sensorDataService;

    @Autowired
    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @PostMapping
    public ResponseEntity<?> postSensorData(@RequestBody com.adidas.model.entity.SensorData sensorData) {
    	sensorDataService.save(sensorData);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(sensorData.getIdSensorData())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(sensorData);

    }

    /********************************** HELPER METHOD **********************************/
//    private void assertBookExist(Long bookId) {
//        bookService
//                .getBookById(bookId)
//                .orElseThrow(() -> new ResourceNotFoundException()
//                        .setResourceName(ResourceNameConstant.BOOK)
//                        .setId(bookId));
//    }

}
