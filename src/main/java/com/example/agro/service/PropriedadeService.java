/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.service;

import com.example.agro.dto.CulturaDTO;
import com.example.agro.dto.PropriedadeDTO;
import com.example.agro.enumeration.TipoProprietario;
import com.example.agro.exceptions.InvalidCpfCnpjException;
import com.example.agro.exceptions.InvalidCulturaException;
import com.example.agro.exceptions.InvalidTotalAreaException;
import com.example.agro.model.Cultura;
import com.example.agro.model.Propriedade;
import com.example.agro.model.Proprietario;
import com.example.agro.repository.PropriedadeRepository;
import com.example.agro.util.CpfCnpjUtil;
import com.example.agro.util.CulturaUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author evpro
 */
@Service
public class PropriedadeService {
    
    @Autowired
    PropriedadeRepository propriedadeRepository;
    
    ProprietarioService proprietario_service;

    PropriedadeService(final ProprietarioService service){
        this.proprietario_service = service;
    }
    
    public Propriedade createPropriedade(PropriedadeDTO propriedade_dto){        
       validaPropriedade(propriedade_dto);
        
       Propriedade propriedade = new Propriedade();
        
       Proprietario proprietario;
       if(!proprietario_service.existsByCpfcnpj(propriedade_dto.getCpfcnpj())){          
           proprietario = new Proprietario();
           proprietario.setTipo(CpfCnpjUtil.getTipoProprietario(propriedade_dto.getCpfcnpj()));
           proprietario.setCpfCnpj(propriedade_dto.getCpfcnpj());
           proprietario.setNome(propriedade_dto.getNome_proprietario());
           proprietario_service.saveProprietario(proprietario);
       } else {
           proprietario = proprietario_service.findByCpfcnpj(propriedade_dto.getCpfcnpj());
           proprietario.setNome(propriedade_dto.getNome_proprietario());
           proprietario_service.saveProprietario(proprietario);
       }
       
       propriedade.setProprietario(proprietario);
       propriedade.setNome(propriedade_dto.getNome());
       propriedade.setCidadeIbge(propriedade_dto.getCidade_ibge());
       propriedade.setEstadoIbge(propriedade_dto.getEstado_ibge());
       propriedade.setAreaTotalHa(propriedade_dto.getArea_total_ha());
       propriedade.setAreaAgricultavelHa(propriedade_dto.getArea_agricultavel_ha());
       propriedade.setAreaVegetacaoHa(propriedade_dto.getArea_vegetacao_ha());
       
       List<Cultura> culturas = new ArrayList<Cultura>();
       for (CulturaDTO cultura_dto : propriedade_dto.getCulturas()){
           Cultura cultura = new Cultura();
           cultura.setAreaPlantadaHa(cultura_dto.getArea_plantada_ha());
           cultura.setCultura(CulturaUtil.getCultura(cultura_dto.getCultura()));
           cultura.setPropriedade(propriedade);
           culturas.add(cultura);
       }
       propriedade.setCulturas(culturas);
       return propriedadeRepository.save(propriedade);
    }
    
    private void validaPropriedade(PropriedadeDTO propriedade_dto){
        String cpf_cnpj = propriedade_dto.getCpfcnpj();
        if(!CpfCnpjUtil.validaFormato(cpf_cnpj)){
            throw new InvalidCpfCnpjException();
        }
        TipoProprietario tipoProprietario = CpfCnpjUtil.getTipoProprietario(cpf_cnpj);
        if(tipoProprietario == TipoProprietario.PF){
            if(!CpfCnpjUtil.validaCpf(cpf_cnpj)){
                throw new InvalidCpfCnpjException();
            } 
        } else if (tipoProprietario == TipoProprietario.PJ){
            if(!CpfCnpjUtil.validaCnpj(cpf_cnpj)){
                throw new InvalidCpfCnpjException();
            }  
        }
        
        Double area_total = propriedade_dto.getArea_total_ha();
        Double area_agricultavel = propriedade_dto.getArea_agricultavel_ha();
        Double area_vegetacao = propriedade_dto.getArea_vegetacao_ha();
        
        if((area_total < 0.0) || (area_agricultavel < 0.0) || (area_vegetacao < 0.0)){
            throw new InvalidTotalAreaException();
        }
        
        if(area_total < area_agricultavel + area_vegetacao){
            throw new InvalidTotalAreaException();
        }
        
        List<CulturaDTO> culturas = propriedade_dto.getCulturas();
        Double area_culturas = 0.0;
        for (CulturaDTO cultura_dto : culturas){
           if(CulturaUtil.getCultura(cultura_dto.getCultura()) == null){
               throw new InvalidCulturaException();
           }
           area_culturas += cultura_dto.getArea_plantada_ha();
        }
        
        if(area_agricultavel < area_culturas){
            throw new InvalidTotalAreaException();
        }
        
        
    }
    
}


