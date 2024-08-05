/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.model;

import com.example.agro.enumeration.TipoProprietario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
/**
 *
 * @author evpro
 */
@Entity
@Table(name="PROPRIETARIO")
public class Proprietario {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name="CPFCNPJ", length=100, nullable=false, unique=true)
    private String cpfcnpj;
    
    @Column(name="TIPO", nullable=false)
    private TipoProprietario tipo;
    
    @Column(name="NOME", length=100, nullable=false, unique=false)
    private String nome;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proprietario", orphanRemoval = false)
    private List<Propriedade> propriedades;
    
    public long getId(){
        return this.id;
    }
    
    public String getCpfCnpj(){
        return this.cpfcnpj;
    }
    
    public void setCpfCnpj(String cpf_cnpj){
        this.cpfcnpj = cpf_cnpj;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public TipoProprietario getTipo(){
        return this.tipo;
    }
    
    public void setTipo(TipoProprietario tipo){
        this.tipo = tipo;
    }
    
}
