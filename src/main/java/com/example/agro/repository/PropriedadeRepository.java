/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.repository;

import com.example.agro.model.Propriedade;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.agro.dto.AreaPlantadaEstadoDTO;

/**
 *
 * @author evpro
 */
@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {
    
    @Query("SELECT SUM(area_total_ha) FROM Propriedade")
    Optional<Double> getAreaTotal();
    
    @Query("SELECT SUM(area_agricultavel_ha) FROM Propriedade")
    Optional<Double> getAreaAgricultavel();
    
    @Query("SELECT SUM(area_vegetacao_ha) FROM Propriedade")
    Optional<Double> getAreaVegetacao();
    
    @Query("SELECT p.estado_ibge as estado, SUM(area_total_ha) as areaTotalEstado FROM Propriedade p group by estado_ibge")
    Optional<List<AreaPlantadaEstadoDTO>> getAreaPorEstado();
    
}
