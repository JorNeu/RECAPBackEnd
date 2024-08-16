package com.recap.controllers;

import com.recap.dto.EstablecimientoDTO;
import com.recap.entities.Establecimiento;
import com.recap.repositories.EstablecimientoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/establecimientos")
public class EstablecimientoController {

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<EstablecimientoDTO> getAllEstablecimientos() {
        return establecimientoRepository.findAll().stream()
                .map(establecimiento -> modelMapper.map(establecimiento, EstablecimientoDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<EstablecimientoDTO> createEstablecimiento(@RequestBody EstablecimientoDTO establecimientoDTO) {
        Establecimiento establecimiento = modelMapper.map(establecimientoDTO, Establecimiento.class);
        establecimiento = establecimientoRepository.save(establecimiento);
        return ResponseEntity.ok(modelMapper.map(establecimiento, EstablecimientoDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablecimientoDTO> getEstablecimientoById(@PathVariable Long id) {
        return establecimientoRepository.findById(id)
                .map(establecimiento -> ResponseEntity.ok(modelMapper.map(establecimiento, EstablecimientoDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstablecimientoDTO> updateEstablecimiento(@PathVariable Long id, @RequestBody EstablecimientoDTO establecimientoDTO) {
        return establecimientoRepository.findById(id)
                .map(establecimiento -> {
                    modelMapper.map(establecimientoDTO, establecimiento);
                    establecimiento = establecimientoRepository.save(establecimiento);
                    return ResponseEntity.ok(modelMapper.map(establecimiento, EstablecimientoDTO.class));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEstablecimiento(@PathVariable Long id) {
        return establecimientoRepository.findById(id)
                .map(establecimiento -> {
                    establecimientoRepository.delete(establecimiento);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}