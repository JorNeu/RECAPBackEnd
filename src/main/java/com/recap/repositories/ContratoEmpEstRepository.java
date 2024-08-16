package com.recap.repositories;

import com.recap.entities.ContratoEmpEst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContratoEmpEstRepository extends JpaRepository<ContratoEmpEst, Long> {
}