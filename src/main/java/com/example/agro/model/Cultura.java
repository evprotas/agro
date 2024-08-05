/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.model;

import com.example.agro.enumeration.CulturaEnum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author evpro
 */
@Entity
@Table(name="CULTURA")
public class Cultura {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name="CULTURA", nullable=false)
    private CulturaEnum cultura;
    
    @Column(name="AREA_PLANTADA_HA", nullable=false)
    private Double area_plantada_ha;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonProperty("propriedade")
    private Propriedade propriedade;
    
    public Long getId(){
        return this.id;
    }
    
    public Double getAreaPlantadaHa(){
        return this.area_plantada_ha;
    }
    
    public void setAreaPlantadaHa(Double area_plantada_ha){
        this.area_plantada_ha = area_plantada_ha;
    }
    
    public CulturaEnum getCultura(){
        return this.cultura;
    }
    
    public void setCultura(CulturaEnum cultura){
        this.cultura = cultura;
    }
    
    public Propriedade getPropriedade(){
        return this.propriedade;
    }
    
    public void setPropriedade(Propriedade propriedade){
        this.propriedade = propriedade;
    }
}
