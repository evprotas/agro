/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.service;

import com.example.agro.enumeration.CulturaEnum;
import com.example.agro.model.Cultura;
import com.example.agro.repository.CulturaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agro.dto.AreaPlantadaCulturaDTO;

/**
 *
 * @author evpro
 */
@Service
public class CulturaService {
    
    @Autowired
    CulturaRepository cultura_repository;
    
    public List<AreaPlantadaCulturaDTO> getAreaPlantadaPorCultura(){
        return cultura_repository.getAreaPlantadaByCultura().get();
    }
    
    public void deleteCultura(Cultura cultura){
        cultura_repository.delete(cultura);
    }
}
