package com.recap.service;

import com.recap.entities.Contrato;
import com.recap.repositories.ContratoRepository;
import com.recap.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    public int getCantidadControladores(Long idEmpresa) {
        List<Contrato> contratos = contratoRepository.findByEmpresa_IdEmpresa(idEmpresa);
        return contratos.size();
    }
}