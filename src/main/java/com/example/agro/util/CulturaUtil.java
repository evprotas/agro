/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.util;

import com.example.agro.enumeration.CulturaEnum;
import org.springframework.stereotype.Component;

/**
 *
 * @author evpro
 */
@Component
public class CulturaUtil {
    public static CulturaEnum getCultura(String cultura){
        cultura = cultura.toLowerCase();
        for (CulturaEnum c : CulturaEnum.values()){
            if(c.toString().toLowerCase().equals(cultura) || 
                    c.name().toLowerCase().equals(cultura)){
               return c;
            }
        }
        return null;
    }
}
