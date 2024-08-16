package com.recap.controllers;

import com.recap.dto.EmpresaDTO;
import com.recap.entities.Empresa;
import com.recap.repositories.EmpresaRepository;
import com.recap.service.EmpresaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public List<EmpresaDTO> getAllEmpresas() {
        return empresaRepository.findAll().stream()
                .map(empresa -> modelMapper.map(empresa, EmpresaDTO.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/{idEmpresa}/controladores")
    public ResponseEntity<Integer> getCantidadControladores(@PathVariable Long idEmpresa) {
        int cantidadControladores = empresaService.getCantidadControladores(idEmpresa);
        return ResponseEntity.ok(cantidadControladores);
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> createEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        Empresa empresa = modelMapper.map(empresaDTO, Empresa.class);
        empresa = empresaRepository.save(empresa);
        return ResponseEntity.ok(modelMapper.map(empresa, EmpresaDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> getEmpresaById(@PathVariable Long id) {
        return empresaRepository.findById(id)
                .map(empresa -> ResponseEntity.ok(modelMapper.map(empresa, EmpresaDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> updateEmpresa(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO) {
        return empresaRepository.findById(id)
                .map(empresa -> {
                    modelMapper.map(empresaDTO, empresa);
                    empresa = empresaRepository.save(empresa);
                    return ResponseEntity.ok(modelMapper.map(empresa, EmpresaDTO.class));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmpresa(@PathVariable Long id) {
        return empresaRepository.findById(id)
                .map(empresa -> {
                    empresaRepository.delete(empresa);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}