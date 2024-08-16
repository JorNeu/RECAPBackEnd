package com.recap.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "contratosEmpEst")
@Getter
@Setter
public class ContratoEmpEst {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContratoEmpEst;

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date alta;

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date vencimiento;

    @Lob
    private String observaciones;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "idEstablecimiento", nullable = false)
    private Establecimiento establecimiento;
}