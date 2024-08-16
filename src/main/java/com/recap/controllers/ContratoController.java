package com.recap.controllers;

import com.recap.dto.ContratoDTO;
import com.recap.entities.*;
import com.recap.repositories.ContratoRepository;
import com.recap.repositories.ControladorRepository;
import com.recap.repositories.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoRepository contratoRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ControladorRepository controladorRepository;


    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ContratoDTO> getAllContratos() {
        return contratoRepository.findAll().stream()
                .map(contrato -> modelMapper.map(contrato, ContratoDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ContratoDTO> createContrato(@RequestBody ContratoDTO contratoDTO) {
        Optional<Empresa> empresa = empresaRepository.findById(contratoDTO.getIdEmpresa());
        Optional<Controlador> controlador = controladorRepository.findById(contratoDTO.getIdControlador());

     if (empresa.isEmpty() || controlador.isEmpty()) {
        return ResponseEntity.badRequest().build();
    }

    Contrato contrato = modelMapper.map(contratoDTO, Contrato.class);
        contrato.setEmpresa(empresa.get());
        contrato.setControlador(controlador.get());
        contrato.setAlta(contratoDTO.getAlta());
        contrato.setVencimiento(contratoDTO.getVencimiento());

        contrato= contratoRepository.save(contrato);
        return ResponseEntity.ok(modelMapper.map(contrato, ContratoDTO.class));
}


    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> getContratoById(@PathVariable Long id) {
        return contratoRepository.findById(id)
                .map(contrato -> ResponseEntity.ok(modelMapper.map(contrato, ContratoDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoDTO> updateContrato(@PathVariable Long id, @RequestBody ContratoDTO contratoDTO) {
        return contratoRepository.findById(id)
                .map(contrato -> {
                    modelMapper.map(contratoDTO, contrato);
                    contrato = contratoRepository.save(contrato);
                    return ResponseEntity.ok(modelMapper.map(contrato, ContratoDTO.class));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContrato(@PathVariable Long id) {
        return contratoRepository.findById(id)
                .map(contrato -> {
                    contratoRepository.delete(contrato);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}