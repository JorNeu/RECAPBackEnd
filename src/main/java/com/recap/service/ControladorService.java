package com.recap.service;

import com.recap.entities.Contrato;
import com.recap.repositories.ContratoRepository;
import com.recap.repositories.ControladorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControladorService {

    @Autowired
    private ControladorRepository controladorRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    public int getCantidadContratos(Long idControlador) {
        List<Contrato> contratos = contratoRepository.findByControlador_IdControlador(idControlador);
        return contratos.size();
    }
}