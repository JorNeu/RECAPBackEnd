package com.recap.repositories;

import com.recap.entities.Controlador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControladorRepository extends JpaRepository<Controlador,Long> {
}