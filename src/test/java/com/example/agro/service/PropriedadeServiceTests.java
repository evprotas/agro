/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.service;

import com.example.agro.dto.CulturaDTO;
import com.example.agro.dto.PropriedadeDTO;
import com.example.agro.exceptions.InvalidCulturaException;
import com.example.agro.exceptions.InvalidTotalAreaException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author evpro
 */
@SpringBootTest
@ActiveProfiles("test")
public class PropriedadeServiceTests {
    
    @Autowired
    private PropriedadeService propriedade_service;
    
    @BeforeAll
    public static void setUp() {

    }
    
    @BeforeEach
    public void setUpTest() {
    }
    
    @AfterAll
    public static void tearDown() {
    }
    
    @Test
    void testCreateSuccess(){
        PropriedadeDTO propriedate_dto = new PropriedadeDTO();
        propriedate_dto.setCpfcnpj("123.123.123-12");
        propriedate_dto.setNome_proprietario("Proprietario teste");
        propriedate_dto.setNome("Teste");
        propriedate_dto.setCidade_ibge("1234567");
        propriedate_dto.setEstado_ibge("12");
        propriedate_dto.setArea_total_ha(100.0);
        propriedate_dto.setArea_agricultavel_ha(50.0);
        propriedate_dto.setArea_vegetacao_ha(50.0);
        
        CulturaDTO cultura_dto = new CulturaDTO();
        cultura_dto.setArea_plantada_ha(10.0);
        cultura_dto.setCultura("Cafe");
        List<CulturaDTO> culturas = new ArrayList<CulturaDTO>();
        culturas.add(cultura_dto);
        
        propriedate_dto.setCulturas(culturas);
        assertDoesNotThrow(() -> propriedade_service.createPropriedade(propriedate_dto));
        
    }
    
    @Test
    void testCreateInvalidCulturaError(){
        PropriedadeDTO propriedate_dto = new PropriedadeDTO();
        propriedate_dto.setCpfcnpj("123.123.123-12");
        propriedate_dto.setNome_proprietario("Proprietario teste");
        propriedate_dto.setNome("Teste");
        propriedate_dto.setCidade_ibge("1234567");
        propriedate_dto.setEstado_ibge("12");
        propriedate_dto.setArea_total_ha(100.0);
        propriedate_dto.setArea_agricultavel_ha(50.0);
        propriedate_dto.setArea_vegetacao_ha(50.0);
        
        CulturaDTO cultura_dto = new CulturaDTO();
        cultura_dto.setArea_plantada_ha(10.0);
        cultura_dto.setCultura("Erro");
        List<CulturaDTO> culturas = new ArrayList<CulturaDTO>();
        culturas.add(cultura_dto);
        
        propriedate_dto.setCulturas(culturas);
        assertThrows(InvalidCulturaException.class,() -> propriedade_service.createPropriedade(propriedate_dto));   
    }
    
    @Test
    void testCreateInvalidAreaError(){
        PropriedadeDTO propriedate_dto = new PropriedadeDTO();
        propriedate_dto.setCpfcnpj("123.123.123-12");
        propriedate_dto.setNome_proprietario("Proprietario teste");
        propriedate_dto.setNome("Teste");
        propriedate_dto.setCidade_ibge("1234567");
        propriedate_dto.setEstado_ibge("12");
        propriedate_dto.setArea_total_ha(100.0);
        propriedate_dto.setArea_agricultavel_ha(50.1);
        propriedate_dto.setArea_vegetacao_ha(50.0);
        
        CulturaDTO cultura_dto = new CulturaDTO();
        cultura_dto.setArea_plantada_ha(10.0);
        cultura_dto.setCultura("Soja");
        List<CulturaDTO> culturas = new ArrayList<CulturaDTO>();
        culturas.add(cultura_dto);
        
        propriedate_dto.setCulturas(culturas);
        assertThrows(InvalidTotalAreaException.class,() -> propriedade_service.createPropriedade(propriedate_dto));   
    }
    
}
