/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.repository;

import com.example.agro.model.Proprietario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author evpro
 */
@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {
    
    boolean existsByCpfcnpj(String cpfcnpf);
    List<Proprietario> findByCpfcnpj(String cpfcnpf);
}
