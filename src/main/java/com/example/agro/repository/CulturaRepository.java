/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.repository;

import com.example.agro.model.Cultura;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author evpro
 */
public interface CulturaRepository extends JpaRepository<Cultura, Long> {
    
}
