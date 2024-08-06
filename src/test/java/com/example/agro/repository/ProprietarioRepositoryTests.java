/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.repository;


import com.example.agro.enumeration.TipoProprietario;
import com.example.agro.model.Proprietario;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author evpro
 */
@ActiveProfiles("test")
@DataJpaTest
public class ProprietarioRepositoryTests {
    
    @Autowired
    private ProprietarioRepository proprietario_repository;
    @Autowired
    TestEntityManager entityManager;
    
    private final String CPF1 = "012.012.012-01";
    private final String CPF2 = "123.123.123-12";
    private final String NOME1 = "Nome teste 1";
    private final TipoProprietario TIPO1 = TipoProprietario.PF;
    
    @BeforeAll
    public static void setUp() {

    }
    
    @BeforeEach
    public void setUpTest() {
        Proprietario proprietario = new Proprietario();
        proprietario.setCpfCnpj(CPF1);
        proprietario.setTipo(TIPO1);
        proprietario.setNome(NOME1);
        proprietario_repository.save(proprietario);
    }
    
    @AfterAll
    public static void tearDown() {
    }
    
    @Test
    void testSaveProprietarioSuccess() {
        Proprietario novo_proprietario = new Proprietario();
        novo_proprietario.setCpfCnpj(CPF2);
        novo_proprietario.setTipo(TipoProprietario.PF);
        novo_proprietario.setNome("Nome teste");
        Proprietario inserted_proprietario = proprietario_repository.save(novo_proprietario);
        assertThat(entityManager.find(Proprietario.class, inserted_proprietario.getId()) ).isEqualTo(novo_proprietario);
    }
    
    @Test
    void testExistsByCpfcnpj() {
        assertTrue(proprietario_repository.existsByCpfcnpj(CPF1));
        assertFalse(proprietario_repository.existsByCpfcnpj(CPF2));
    }
    
    @Test
    void testFindByCpfcnpjSuccess() {
        Proprietario proprietario = proprietario_repository.findByCpfcnpj(CPF1).get(0);
        assertEquals(proprietario.getNome(), NOME1);
        assertEquals(proprietario.getTipo(), TIPO1);
    }
    
    @Test
    void testFindByCpfcnpjFailure() {
        List<Proprietario> proprietario = proprietario_repository.findByCpfcnpj(CPF2);
        assertEquals(proprietario.size(), 0);
    }
    
}
