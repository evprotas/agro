/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.controller;

import com.example.agro.dto.AreaPlantadaCulturaDTO;
import com.example.agro.dto.AreaPlantadaEstadoDTO;
import com.example.agro.dto.PropriedadeDTO;
import com.example.agro.model.Propriedade;
import com.example.agro.service.CulturaService;
import com.example.agro.service.PropriedadeService;
import java.util.List;
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
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author evpro
 */
@RestController
@RequestMapping("/propriedades")
public class PropriedadeController {

	PropriedadeService propriedade_service;
        CulturaService cultura_service;

        PropriedadeController(final PropriedadeService propriedade_service, final CulturaService cultura_service){
            this.propriedade_service = propriedade_service;
            this.cultura_service = cultura_service;
        }
        
	@PostMapping()
	public ResponseEntity<Propriedade> createPropriedade(@Valid @RequestBody PropriedadeDTO propriedade_dto) {
            Propriedade propriedade = propriedade_service.createPropriedade(propriedade_dto);
            return new ResponseEntity<>(propriedade, HttpStatus.CREATED);
	}
        
        @GetMapping()
	public ResponseEntity<Map<String, Object>> getPropriedades(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
            Map<String, Object> propriedades = propriedade_service.getAllPropriedades(page, size);
            return new ResponseEntity<>(propriedades, HttpStatus.OK);
	}
        
        @DeleteMapping("{id}")
        public ResponseEntity<HttpStatus> deletePropriedade(@PathVariable("id") long id) {
            try {
                propriedade_service.deletePropriedade(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        @GetMapping("{id}")
        public ResponseEntity<Propriedade> getPropriedade(@PathVariable("id") long id) {
            return new ResponseEntity<>(propriedade_service.getPropriedadeById(id), HttpStatus.OK);
        }
        
        @PutMapping("{id}")
        public ResponseEntity<Propriedade> updatePropriedade(@PathVariable("id") long id, @Valid @RequestBody PropriedadeDTO propriedade_dto) {
            Propriedade propriedade = propriedade_service.updatePropriedade(id, propriedade_dto);
            return new ResponseEntity<>(propriedade, HttpStatus.OK);
        }
        
        @PatchMapping("{id}")
        public ResponseEntity<Propriedade> patchPropriedade(@PathVariable("id") long id, @RequestBody PropriedadeDTO propriedade_dto) {
            Propriedade propriedade = propriedade_service.patchPropriedade(id, propriedade_dto);
            return new ResponseEntity<>(propriedade, HttpStatus.OK);
        }
        
        @GetMapping("uso_solo")
        public ResponseEntity<Map<String, Object>> getUsoSolo() {
            return new ResponseEntity<>(propriedade_service.getUsoSolo(), HttpStatus.OK);
        }
        
        @GetMapping("area_por_estado")
        public ResponseEntity<List<AreaPlantadaEstadoDTO>> getAreaPorEstado() {
            return new ResponseEntity<>(propriedade_service.getAreaPorEstado(), HttpStatus.OK);
        }
        
        @GetMapping("area_por_cultura")
        public ResponseEntity<List<AreaPlantadaCulturaDTO>> getAreaPlantadaPorCultura() {
            return new ResponseEntity<>(cultura_service.getAreaPlantadaPorCultura(), HttpStatus.OK);
        }
}
