/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.enumeration;

/**
 *
 * @author evpro
 */
public enum Cultura {
    SOJA("Soja"),
    MILHO("Milho"),
    ALGODAO("Algodão"),
    CAFE("Café"),
    CANA("Cana de açucar");
    
    private String display_name;

    Cultura(String display_name) {
        this.display_name = display_name;
    }

    public String getDisplayName() {
        return this.display_name;
    }
}
