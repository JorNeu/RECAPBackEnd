package com.recap.repositories;

import com.recap.entities.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato,Long> {
    List<Contrato> findByEmpresa_IdEmpresa(Long idEmpresa);
    List<Contrato> findByControlador_IdControlador(Long idControlador);

}