package com.adidas.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adidas.constant.PageConstant;
import com.adidas.constant.ResourceNameConstant;
import com.adidas.model.dto.PaginatedResult;
import com.adidas.model.entity.Book;
import com.adidas.service.BookService;
import com.adidas.service.UserActivityDataService;
import com.adidas.util.PageUtil;
import com.adidas.web.exception.ResourceNotFoundException;

import java.net.URI;

/**
 * @author Error404
 */
@RestController
@RequestMapping("/userActivityData")
public class UserActivityDataController {

    private UserActivityDataService userActivityDataService;

    @Autowired
    public UserActivityDataController(UserActivityDataService userActivityDataService) {
        this.userActivityDataService = userActivityDataService;
    }

//    @PostMapping
//    public ResponseEntity<?> postUserActivityData(@RequestBody com.adidas.model.entity.UserActivityData userActivityData) {
//    	userActivityDataService.save(userActivityData);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("")
//                .buildAndExpand(userActivityData.getIdUserActivityData())
//                .toUri();
//
//        return ResponseEntity
//                .created(location)
//                .body(userActivityData);
//
//    }
    
    @PostMapping
    public ResponseEntity<?> postUserActivityData(@RequestBody com.adidas.model.entity.UserActivityData userActivityData) {
    	userActivityDataService.saveFull(userActivityData);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/")
                .buildAndExpand(userActivityData.getIdUserActivityData())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(userActivityData);

    }
    
    @GetMapping("/idUser/{idUser}")
    public ResponseEntity<?> getActivitiesByUserId(@PathVariable Long idUser) {
        // Parse request parameters
       return ResponseEntity
               .ok(userActivityDataService.getActivitiesByUserId(idUser));
    }
    
    @GetMapping("/idUser/{idUser}/idActivity/{idActivity}")
    public ResponseEntity<?> getUserActivityDataByUserActivity(@PathVariable Long idUser, @PathVariable Long idActivity) {
        // Parse request parameters
       return ResponseEntity
               .ok(userActivityDataService.getUserActivityDataByUserActivity(idUser, idActivity));
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
