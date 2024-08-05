/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.util;

import org.springframework.stereotype.Component;

/**
 *
 * @author evpro
 */
@Component
public class IbgeUtil {
    
    private static final String CIDADE_IBGE_REGEX = "^[0-9]{7}$";
    private static final String ESTADO_IBGE_REGEX = "^[0-9]{2}$";
    
    public static boolean validaCidade(String cidade_ibge){
        return cidade_ibge.matches(CIDADE_IBGE_REGEX);
    }
    
    public static boolean validaEstado(String estado_ibge){
        return estado_ibge.matches(ESTADO_IBGE_REGEX);
    }
}
