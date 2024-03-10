package com.practice.library.controoller;

import com.practice.library.dto.RentRequestDTO;
import com.practice.library.model.Rent;
import com.practice.library.service.interfaces.RentService;
import exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentController {
    @Autowired
    RentService rentService;

    @PostMapping("/rent-books")
    public ResponseEntity<Rent> rentBook(@RequestBody RentRequestDTO rentRequestDTO) {
        Rent rent;
        try {
            rent = rentService.rentBook(rentRequestDTO);
        } catch (MyException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(rent, HttpStatus.OK);
    }

    @PutMapping("/return-books/{rentId}")
    public ResponseEntity<Boolean> returnBooks(@PathVariable Long rentId) {
        boolean response;
        try {
            response = rentService.returnBook(rentId);
        } catch (MyException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/rentBooks")
    public ResponseEntity<List> rentBooks() {
        return ResponseEntity.ok().body(rentService.rentBooks());
    }
}
