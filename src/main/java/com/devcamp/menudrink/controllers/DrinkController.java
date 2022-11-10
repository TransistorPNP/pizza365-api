package com.devcamp.menudrink.controllers;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devcamp.menudrink.models.Drink;
import com.devcamp.menudrink.repository.IDrinkRepository;

@RestController
@CrossOrigin
public class DrinkController {
    @Autowired
    IDrinkRepository drinkRepository;

    @GetMapping("/drinks")
    public ResponseEntity<Object> getAllDrink() {
        try {
            return new ResponseEntity<>(drinkRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/drinks/{id}")
    public ResponseEntity<Object> getDrinkById(@PathVariable Integer id) {
        try {
            Optional<Drink> optional = drinkRepository.findById(id);
            if (optional.isPresent()) {
                return new ResponseEntity<>(optional.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/drinks")
    public ResponseEntity<Object> createDrink(@Valid @RequestBody Drink newDrink) {
        try {
            newDrink.setNgayTao(new Date());
            newDrink.setNgayCapNhat(new Date());
            Drink saved = drinkRepository.save(newDrink);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/drinks/{id}")
    public ResponseEntity<Object> updateDrinkById(@Valid @RequestBody Drink newDrink, @PathVariable Integer id) {
        try {
            Optional<Drink> optional = drinkRepository.findById(id);
            if (!optional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Drink thisDrink = optional.get();

            thisDrink.setMaNuocUong(newDrink.getMaNuocUong());
            thisDrink.setTenNuocUong(newDrink.getTenNuocUong());
            if (newDrink.getDonGia() != 0) {
                thisDrink.setDonGia(newDrink.getDonGia());
            }
            thisDrink.setNgayCapNhat(new Date());
            Drink saved = drinkRepository.save(thisDrink);
            return new ResponseEntity<>(saved, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/drinks/{id}")
    public ResponseEntity<Object> deleteDrinkById(@PathVariable Integer id) {
        try {
            Optional<Drink> optional = drinkRepository.findById(id);
            if (optional.isPresent()) {
                drinkRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.unprocessableEntity().body("Không tồn tại nước uống với ID này!");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
