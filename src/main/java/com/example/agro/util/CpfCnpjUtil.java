/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.agro.enumeration.TipoProprietario;
import org.springframework.stereotype.Component;


/**
 *
 * @author evpro
 */
@Component
public class CpfCnpjUtil {
    
    private static final String CPF_REGEX = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$";
    private static final String CNPJ_REGEX = "^[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}$";

    public static boolean validaFormato(String cpf_cnpj){
        if(cpf_cnpj.matches(CPF_REGEX)){
            return true;
        } else if(cpf_cnpj.matches(CNPJ_REGEX)){
            return true;
        }
        else return false;
    }
    
    public static TipoProprietario getTipoProprietario(String cpf_cnpj){
        if(cpf_cnpj.matches(CPF_REGEX)){
            return TipoProprietario.PF;
        } else if(cpf_cnpj.matches(CNPJ_REGEX)){
            return TipoProprietario.PJ;
        }
        else return null;
    }
    
    public static boolean validaCpf(String cpf){
        return true;
    }
    
    public static boolean validaCnpj(String cpf){
        return true;
    }
}
