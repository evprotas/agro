/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.service;

import com.example.agro.model.Proprietario;
import com.example.agro.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author evpro
 */
@Service
public class ProprietarioService {
    
    @Autowired
    ProprietarioRepository proprietarioRepository;
    
    public boolean existsByCpfcnpj(String cpfcnpj){
        return proprietarioRepository.existsByCpfcnpj(cpfcnpj);
    }
    
    public Proprietario findByCpfcnpj(String cpfcnpj){
        return proprietarioRepository.findByCpfcnpj(cpfcnpj).get(0);
    }
    
    public Proprietario saveProprietario(Proprietario proprietario){
        return proprietarioRepository.save(proprietario);
    }
    
}
