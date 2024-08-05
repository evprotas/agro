/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.util;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.agro.enumeration.CulturaEnum;

/**
 *
 * @author evpro
 */
@SpringBootTest
@ActiveProfiles("test")
public class CulturaUtilTests {
    @Autowired
    private CulturaUtil culturaUtil;
    
    @Test
    void testGetCulturaEmptyString(){
        assertNull(culturaUtil.getCultura(""));
    }
    
    @Test
    void testGetCulturaAlgodao(){
        assertEquals(CulturaEnum.ALGODAO, culturaUtil.getCultura("Algodão"));
        assertEquals(CulturaEnum.ALGODAO, culturaUtil.getCultura("algodao"));
        assertEquals(CulturaEnum.ALGODAO, culturaUtil.getCultura("ALGODAO"));
        assertEquals(CulturaEnum.ALGODAO, culturaUtil.getCultura("ALGODÃO"));
    }
    
    @Test
    void testGetCulturaCana(){
        assertEquals(CulturaEnum.CANA, culturaUtil.getCultura("cana"));
        assertEquals(CulturaEnum.CANA, culturaUtil.getCultura("CANA"));
        assertEquals(CulturaEnum.CANA, culturaUtil.getCultura("cana de açucar"));
        assertEquals(CulturaEnum.CANA, culturaUtil.getCultura("CANA DE AÇUCAR"));
    }
    
}
