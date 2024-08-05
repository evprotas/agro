/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.agro.service;

import com.example.agro.dto.AreaPlantadaEstadoDTO;
import com.example.agro.dto.CulturaDTO;
import com.example.agro.dto.PropriedadeDTO;
import com.example.agro.enumeration.TipoProprietario;
import com.example.agro.exceptions.InvalidCpfCnpjException;
import com.example.agro.exceptions.InvalidCulturaException;
import com.example.agro.exceptions.InvalidIbgeCodeException;
import com.example.agro.exceptions.InvalidProprietarioException;
import com.example.agro.exceptions.InvalidTotalAreaException;
import com.example.agro.exceptions.PropriedadeDoesNotExistException;
import com.example.agro.model.Cultura;
import com.example.agro.model.Propriedade;
import com.example.agro.model.Proprietario;
import com.example.agro.repository.PropriedadeRepository;
import com.example.agro.util.CpfCnpjUtil;
import com.example.agro.util.CulturaUtil;
import com.example.agro.util.IbgeUtil;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author evpro
 */
@Service
public class PropriedadeService {
    
    @Autowired
    PropriedadeRepository propriedade_repository;
    
    ProprietarioService proprietario_service;
    CulturaService cultura_service;

    PropriedadeService(final ProprietarioService proprietario_service, 
            final CulturaService cultura_service){
        this.proprietario_service = proprietario_service;
        this.cultura_service = cultura_service;
    }
    
    public Map<String, Object> getAllPropriedades(Integer page, Integer size){
        Pageable paging = PageRequest.of(page, size);
        Page<Propriedade> pagePropriedades;
        pagePropriedades = propriedade_repository.findAll(paging);
        Map<String, Object> response = new HashMap<>();
        List<Propriedade> propriedades = new ArrayList<Propriedade>();
        propriedades = pagePropriedades.getContent();
        response.put("propriedades", propriedades);
        response.put("currentPage", pagePropriedades.getNumber());
        response.put("totalItems", pagePropriedades.getTotalElements());
        response.put("totalPages", pagePropriedades.getTotalPages());
        return response;
    }
    
    public Propriedade getPropriedadeById(Long id){
        return propriedade_repository.findById(id).get();
    }
    
    @Transactional
    public Propriedade createPropriedade(PropriedadeDTO propriedade_dto){
       validaPropriedadeDTO(propriedade_dto);
       
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
       return propriedade_repository.save(propriedade);
    }
    
    @Transactional
    public Propriedade updatePropriedade(Long id, PropriedadeDTO propriedade_dto){
          
       Propriedade propriedade;
       Optional<Propriedade> propriedade_data = propriedade_repository.findById(id);
       if(!propriedade_data.isPresent()){
           throw new PropriedadeDoesNotExistException();
       } else{
           propriedade = propriedade_data.get();
       }
       validaPropriedadeDTO(propriedade_dto);
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
       
       List<Cultura> culturas = propriedade.getCulturas();
       culturas.clear();
       for (CulturaDTO cultura_dto : propriedade_dto.getCulturas()){
           Cultura cultura = new Cultura();
           cultura.setAreaPlantadaHa(cultura_dto.getArea_plantada_ha());
           cultura.setCultura(CulturaUtil.getCultura(cultura_dto.getCultura()));
           cultura.setPropriedade(propriedade);
           culturas.add(cultura);
       }
       propriedade.setCulturas(culturas);
       return propriedade_repository.save(propriedade);
    }
    
    @Transactional
    public Propriedade patchPropriedade(Long id, PropriedadeDTO propriedade_dto){
          
       Propriedade propriedade;
       Optional<Propriedade> propriedade_data = propriedade_repository.findById(id);
       if(!propriedade_data.isPresent()){
           throw new PropriedadeDoesNotExistException();
       } else{
           propriedade = propriedade_data.get();
       }
       
       validaPropriedadePatch(propriedade, propriedade_dto);
       
       if(propriedade_dto.getCpfcnpj() != null){
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
       }
       
       if(propriedade_dto.getNome() != null){
           propriedade.setNome(propriedade_dto.getNome());
       }
       
       if(propriedade_dto.getCidade_ibge() != null){
           propriedade.setCidadeIbge(propriedade_dto.getCidade_ibge());
       }
       
       if(propriedade_dto.getEstado_ibge() != null){
           propriedade.setEstadoIbge(propriedade_dto.getEstado_ibge());
       }
       
       if(propriedade_dto.getArea_total_ha() != null){
           propriedade.setAreaTotalHa(propriedade_dto.getArea_total_ha());
       }
       
       if(propriedade_dto.getArea_agricultavel_ha() != null){
           propriedade.setAreaAgricultavelHa(propriedade_dto.getArea_agricultavel_ha());
       }
       
       if(propriedade_dto.getArea_vegetacao_ha() != null){
           propriedade.setAreaVegetacaoHa(propriedade_dto.getArea_vegetacao_ha());
       }
       
       if(propriedade_dto.getCulturas() != null){
            List<Cultura> culturas = propriedade.getCulturas();
            culturas.clear();
            for (CulturaDTO cultura_dto : propriedade_dto.getCulturas()){
                Cultura cultura = new Cultura();
                cultura.setAreaPlantadaHa(cultura_dto.getArea_plantada_ha());
                cultura.setCultura(CulturaUtil.getCultura(cultura_dto.getCultura()));
                cultura.setPropriedade(propriedade);
                culturas.add(cultura);
            }
            propriedade.setCulturas(culturas);
       }
       return propriedade_repository.save(propriedade);
    }
    
    public void deletePropriedade(Long id){
        propriedade_repository.deleteById(id);
    }
    
    private void validaCpfCnpj(String cpf_cnpj){
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
    }
    
    private void validaArea(Double area_total, Double area_agricultavel, Double area_vegetacao){
        if((area_total < 0.0) || (area_agricultavel < 0.0) || (area_vegetacao < 0.0)){
            throw new InvalidTotalAreaException();
        }
        
        if(area_total < area_agricultavel + area_vegetacao){
            throw new InvalidTotalAreaException();
        }
    }
    
    private void validaCidade(String cidade_ibge){
        if(!IbgeUtil.validaCidade(cidade_ibge)){
            throw new InvalidIbgeCodeException();
        }
    }
    
    private void validaEstado(String estado_ibge){
        if(!IbgeUtil.validaEstado(estado_ibge)){
            throw new InvalidIbgeCodeException();
        }
    }
    
    private void validaCulturas(Double area_agricultavel, List<CulturaDTO> culturas){
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
    
    private void validaPropriedadeDTO(PropriedadeDTO propriedade_dto){
        validaCpfCnpj(propriedade_dto.getCpfcnpj());
        
        Double area_total = propriedade_dto.getArea_total_ha();
        Double area_agricultavel = propriedade_dto.getArea_agricultavel_ha();
        Double area_vegetacao = propriedade_dto.getArea_vegetacao_ha();
        validaArea(area_total, area_agricultavel, area_vegetacao);
        
        validaCidade(propriedade_dto.getCidade_ibge());
        
        validaEstado(propriedade_dto.getEstado_ibge());
        
        List<CulturaDTO> culturas = propriedade_dto.getCulturas();
        
        validaCulturas(area_agricultavel, culturas);
        
    }
    
    private void validaPropriedadePatch(Propriedade propriedade, PropriedadeDTO propriedade_dto){
       
       if(propriedade_dto.getCpfcnpj()!= null){
           if(propriedade_dto.getNome_proprietario() == null){
               throw new InvalidProprietarioException();
           }
           validaCpfCnpj(propriedade_dto.getCpfcnpj());  
       }
       
       Double area_total;
       Double area_agricultavel;
       Double area_vegeracao;     
       
       if(propriedade_dto.getArea_total_ha() != null){
           area_total = propriedade_dto.getArea_total_ha();
       } else {
           area_total = propriedade.getAreaTotalHa();
       }
       
       if(propriedade_dto.getArea_agricultavel_ha() != null){
           area_agricultavel = propriedade_dto.getArea_agricultavel_ha();
       } else {
           area_agricultavel = propriedade.getAreaAgricultavelHa();
       }
       
       if(propriedade_dto.getArea_vegetacao_ha() != null){
           area_vegeracao = propriedade_dto.getArea_vegetacao_ha();
       } else {
           area_vegeracao = propriedade.getAreaVegetacaoHa();
       }
       
       validaArea(area_total, area_agricultavel, area_vegeracao);
       
       if(propriedade_dto.getCidade_ibge() != null){
           validaCidade(propriedade_dto.getCidade_ibge());
       }
       
       if(propriedade_dto.getEstado_ibge() != null){
           validaEstado(propriedade_dto.getEstado_ibge());
       }
       
       List<CulturaDTO> culturas;
       if(propriedade_dto.getCulturas() != null){
           culturas = propriedade_dto.getCulturas();
       } else {
           culturas = new ArrayList<CulturaDTO>();
           for (Cultura cultura : propriedade.getCulturas()){
                CulturaDTO cultura_dto = new CulturaDTO();
                cultura_dto.setArea_plantada_ha(cultura.getAreaPlantadaHa());
                cultura_dto.setCultura(cultura.getCultura().name());
                culturas.add(cultura_dto);
           }
       }
       
       validaCulturas(area_agricultavel, culturas);
       
    }
    
    public Map<String, Object> getUsoSolo(){
        Map<String, Object> response = new HashMap<>();
        response.put("area_total", propriedade_repository.getAreaTotal().get());
        response.put("area_agricultavel", propriedade_repository.getAreaAgricultavel().get());
        response.put("area_vegetacao", propriedade_repository.getAreaVegetacao().get());
        return response;
    }
    
    public List<AreaPlantadaEstadoDTO> getAreaPorEstado(){
        return propriedade_repository.getAreaPorEstado().get();
    }
}


