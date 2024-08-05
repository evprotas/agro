/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author evpro
 */
@Entity
@Table(name="PROPRIEDADE")
public class Propriedade {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Proprietario proprietario;
    
    @Column(name="NOME", length=100, nullable=false, unique=false)
    private String nome;
    
    @Column(name="CIDADE_IBGE", length=20, nullable=false, unique=false)
    private String cidade_ibge;
    
    @Column(name="ESTADO_IBGE", length=20, nullable=false, unique=false)
    private String estado_ibge;
    
    @Column(name="AREA_TOTAL_HA", nullable=false)
    private Double area_total_ha;
    
    @Column(name="AREA_AGRICULTAVEL_HA", nullable=false)
    private Double area_agricultavel_ha;
    
    @Column(name="AREA_VEGETACAO_HA", nullable=false)
    private Double area_vegetacao_ha;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "propriedade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cultura> culturas;
    
    public long getId(){
        return this.id;
    }
    
    public Proprietario getProprietario(){
        return this.proprietario;
    }
    
    public void setProprietario(Proprietario proprietario){
        this.proprietario = proprietario;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getCidadeIbge(){
        return this.cidade_ibge;
    }
    
    public void setCidadeIbge(String cidade_ibge){
        this.cidade_ibge = cidade_ibge;
    }
    
    public String getEstadoIbge(){
        return this.estado_ibge;
    }
    
    public void setEstadoIbge(String estado_ibge){
        this.estado_ibge = estado_ibge;
    }
    
    public Double getAreaTotalHa(){
        return this.area_total_ha;
    }
    
    public void setAreaTotalHa(Double area_total_ha){
        this.area_total_ha = area_total_ha;
    }
    
    public Double getAreaAgricultavelHa(){
        return this.area_agricultavel_ha;
    }
    
    public void setAreaAgricultavelHa(Double area_agricultavel_ha){
        this.area_agricultavel_ha = area_agricultavel_ha;
    }
    
    public Double getAreaVegetacaoHa(){
        return this.area_vegetacao_ha;
    }
    
    public void setAreaVegetacaoHa(Double area_vegetacao_ha){
        this.area_vegetacao_ha = area_vegetacao_ha;
    }
    
    public List<Cultura> getCulturas(){
        return this.culturas;
    }
    
    public void setCulturas(List<Cultura> culturas){
        this.culturas = culturas;
    }
}