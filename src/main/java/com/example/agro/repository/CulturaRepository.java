/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.repository;

import com.example.agro.model.Cultura;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.agro.dto.AreaPlantadaCulturaDTO;

/**
 *
 * @author evpro
 */
@Repository
public interface CulturaRepository extends JpaRepository<Cultura, Long> {
    
    @Query("SELECT c.cultura as cultura, SUM(area_plantada_ha) as areaPlantada FROM Cultura c group by cultura")
    Optional<List<AreaPlantadaCulturaDTO>> getAreaPlantadaByCultura();
    
}
