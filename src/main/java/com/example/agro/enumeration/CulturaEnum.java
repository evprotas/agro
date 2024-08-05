/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author evpro
 */
public enum CulturaEnum {
    SOJA("Soja"),
    MILHO("Milho"),
    ALGODAO("Algodão"),
    CAFE("Café"),
    CANA("Cana de açucar");
    
    private String nome_exibicao;

    CulturaEnum(String nome_exibicao) {
        this.nome_exibicao = nome_exibicao;
    }
    
    @Override
    @JsonValue
    public String toString() {
        return this.nome_exibicao;
    }
}
