/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.controller;

import com.example.agro.dto.PropriedadeDTO;
import com.example.agro.model.Propriedade;
import com.example.agro.service.PropriedadeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author evpro
 */
@RestController
@RequestMapping("/propriedades")
public class PropriedadeController {

	PropriedadeService propriedade_service;

        PropriedadeController(final PropriedadeService service){
            this.propriedade_service = service;
        }
        
	@PostMapping()
	public ResponseEntity<Propriedade> createPropriedade(@RequestBody PropriedadeDTO propriedade_dto) {
            Propriedade propriedade = propriedade_service.createPropriedade(propriedade_dto);
            return new ResponseEntity<>(propriedade, HttpStatus.CREATED);
	}
}
