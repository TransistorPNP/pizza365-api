package com.devcamp.menudrink.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devcamp.menudrink.models.Menu;
import com.devcamp.menudrink.repository.IMenuRepository;

@RestController
@CrossOrigin
public class MenuController {
    @Autowired
    IMenuRepository menuRepository;

    @GetMapping("/menu")
    public ResponseEntity<Object> getAllMenu() {
        try {
            return new ResponseEntity<>(menuRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<Object> getMenuById(@PathVariable Integer id) {
        try {
            Optional<Menu> optional = menuRepository.findById(id);
            if (optional.isPresent()) {
                return new ResponseEntity<>(optional.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/menu")
    public ResponseEntity<Object> createMenu(@Valid @RequestBody Menu newMenu) {
        try {
            Menu saved = menuRepository.save(newMenu);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/menu/{id}")
    public ResponseEntity<Object> updateMenu(@PathVariable Integer id, @RequestBody Menu updateMenu) {
        try {
            Optional<Menu> optional = menuRepository.findById(id);
            if (!optional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Menu thisMenu = optional.get();

            if (updateMenu.getDuongKinh() != 0) {
                thisMenu.setDuongKinh(updateMenu.getDuongKinh());
            }

            if (updateMenu.getKichThuoc() != null) {
                thisMenu.setKichThuoc(updateMenu.getKichThuoc());
            }

            if (updateMenu.getSalad() != 0) {
                thisMenu.setSalad(updateMenu.getSalad());
            }

            if (updateMenu.getSoLuongNuoc() != 0) {
                thisMenu.setSoLuongNuoc(updateMenu.getSoLuongNuoc());
            }

            if (updateMenu.getSuon() != 0) {
                thisMenu.setSuon(updateMenu.getSuon());
            }

            if (updateMenu.getThanhTien() != 0) {
                thisMenu.setThanhTien(updateMenu.getThanhTien());
            }
            Menu updated = menuRepository.save(thisMenu);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Object> deleteMenuById(@PathVariable Integer id) {
        try {
            if (menuRepository.existsById(id)) {
                menuRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
