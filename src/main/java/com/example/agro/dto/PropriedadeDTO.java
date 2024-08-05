/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.dto;

import java.util.List;

/**
 *
 * @author evpro
 */
public class PropriedadeDTO {

    private Long id;
    
    private String cpfcnpj;
    
    private String nome_proprietario;
    
    private String nome;
    
    private String cidade_ibge;
    
    private String estado_ibge;
    
    private Double area_total_ha;
    
    private Double area_agricultavel_ha;
    
    private Double area_vegetacao_ha;
    
    private List<CulturaDTO> culturas;
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfcnpj() {
        return this.cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public String getNome_proprietario() {
        return this.nome_proprietario;
    }

    public void setNome_proprietario(String nome_proprietario) {
        this.nome_proprietario = nome_proprietario;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade_ibge() {
        return this.cidade_ibge;
    }

    public void setCidade_ibge(String cidade_ibge) {
        this.cidade_ibge = cidade_ibge;
    }

    public String getEstado_ibge() {
        return this.estado_ibge;
    }

    public void setEstado_ibge(String estado_ibge) {
        this.estado_ibge = estado_ibge;
    }

    public Double getArea_total_ha() {
        return this.area_total_ha;
    }

    public void setArea_total_ha(Double area_total_ha) {
        this.area_total_ha = area_total_ha;
    }

    public Double getArea_agricultavel_ha() {
        return this.area_agricultavel_ha;
    }

    public void setArea_agricultavel_ha(Double area_agricultavel_ha) {
        this.area_agricultavel_ha = area_agricultavel_ha;
    }

    public Double getArea_vegetacao_ha() {
        return this.area_vegetacao_ha;
    }

    public void setArea_vegetacao_ha(Double area_vegetacao_ha) {
        this.area_vegetacao_ha = area_vegetacao_ha;
    }

    public List<CulturaDTO> getCulturas() {
        return this.culturas;
    }

    public void setCulturas(List<CulturaDTO> culturas) {
        this.culturas = culturas;
    }
    
}
