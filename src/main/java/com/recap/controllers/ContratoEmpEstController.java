package com.recap.controllers;

import com.recap.dto.ContratoEmpEstDTO;
import com.recap.entities.ContratoEmpEst;
import com.recap.entities.Empresa;
import com.recap.entities.Establecimiento;
import com.recap.repositories.ContratoEmpEstRepository;
import com.recap.repositories.EmpresaRepository;
import com.recap.repositories.EstablecimientoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/contratosEmpEst")
public class ContratoEmpEstController {

    @Autowired
    private ContratoEmpEstRepository contratoEmpEstRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ContratoEmpEstDTO> getAllContratosEmpEst() {
        return contratoEmpEstRepository.findAll().stream()
                .map(contratoEmpEst -> modelMapper.map(contratoEmpEst, ContratoEmpEstDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ContratoEmpEstDTO> createContratoEmpEst(@RequestBody ContratoEmpEstDTO contratoEmpEstDTO) {
        Optional<Empresa> empresa = empresaRepository.findById(contratoEmpEstDTO.getIdEmpresa());
        Optional<Establecimiento> establecimiento = establecimientoRepository.findById(contratoEmpEstDTO.getIdEstablecimiento());

        if (empresa.isEmpty() || establecimiento.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ContratoEmpEst contratoEmpEst = modelMapper.map(contratoEmpEstDTO, ContratoEmpEst.class);
        contratoEmpEst.setEmpresa(empresa.get());
        contratoEmpEst.setEstablecimiento(establecimiento.get());
        contratoEmpEst.setAlta(contratoEmpEstDTO.getAlta());
        contratoEmpEst.setVencimiento(contratoEmpEstDTO.getVencimiento());
        contratoEmpEst.setObservaciones(contratoEmpEstDTO.getObservaciones());

        contratoEmpEst = contratoEmpEstRepository.save(contratoEmpEst);
        return ResponseEntity.ok(modelMapper.map(contratoEmpEst, ContratoEmpEstDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoEmpEstDTO> getContratoEmpEstById(@PathVariable Long id) {
        return contratoEmpEstRepository.findById(id)
                .map(contratoEmpEst -> ResponseEntity.ok(modelMapper.map(contratoEmpEst, ContratoEmpEstDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoEmpEstDTO> updateContratoEmpEst(@PathVariable Long id, @RequestBody ContratoEmpEstDTO contratoEmpEstDTO) {
        return (ResponseEntity<ContratoEmpEstDTO>) contratoEmpEstRepository.findById(id)
                .map(contratoEmpEst -> {
                    Optional<Empresa> empresa = empresaRepository.findById(contratoEmpEstDTO.getIdEmpresa());
                    Optional<Establecimiento> establecimiento = establecimientoRepository.findById(contratoEmpEstDTO.getIdEstablecimiento());

                    if (empresa.isEmpty() || establecimiento.isEmpty()) {
                        return ResponseEntity.badRequest().build();
                    }

                    modelMapper.map(contratoEmpEstDTO, contratoEmpEst);
                    contratoEmpEst.setEmpresa(empresa.get());
                    contratoEmpEst.setEstablecimiento(establecimiento.get());

                    contratoEmpEst = contratoEmpEstRepository.save(contratoEmpEst);
                    return ResponseEntity.ok(modelMapper.map(contratoEmpEst, ContratoEmpEstDTO.class));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContratoEmpEst(@PathVariable Long id) {
        return contratoEmpEstRepository.findById(id)
                .map(contratoEmpEst -> {
                    contratoEmpEstRepository.delete(contratoEmpEst);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}