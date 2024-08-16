package com.recap.controllers;

import com.recap.dto.ControladorDTO;
import com.recap.entities.Controlador;
import com.recap.repositories.ControladorRepository;
import com.recap.service.ControladorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/controladores")
public class ControladorController {

    @Autowired
    private ControladorRepository controladorRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ControladorService controladorService;

    @GetMapping
    public List<ControladorDTO> getAllControladores() {
        return controladorRepository.findAll().stream()
                .map(controlador -> modelMapper.map(controlador, ControladorDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ControladorDTO> createControlador(@RequestBody ControladorDTO controladorDTO) {
        Controlador controlador = modelMapper.map(controladorDTO, Controlador.class);
        controlador = controladorRepository.save(controlador);
        return ResponseEntity.ok(modelMapper.map(controlador, ControladorDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ControladorDTO> getControladorById(@PathVariable Long id) {
        return controladorRepository.findById(id)
                .map(controlador -> ResponseEntity.ok(modelMapper.map(controlador, ControladorDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{idControlador}/contratos")
    public ResponseEntity<Integer> getCantidadContratos(@PathVariable Long idControlador) {
        int cantidadContratos = controladorService.getCantidadContratos(idControlador);
        return ResponseEntity.ok(cantidadContratos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ControladorDTO> updateControlador(@PathVariable Long id, @RequestBody ControladorDTO controladorDTO) {
        return controladorRepository.findById(id)
                .map(controlador -> {
                    modelMapper.map(controladorDTO, controlador);
                    controlador = controladorRepository.save(controlador);
                    return ResponseEntity.ok(modelMapper.map(controlador, ControladorDTO.class));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteControlador(@PathVariable Long id) {
        return controladorRepository.findById(id)
                .map(controlador -> {
                    controladorRepository.delete(controlador);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}