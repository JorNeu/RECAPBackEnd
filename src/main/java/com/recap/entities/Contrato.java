package com.recap.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "contratos")
@Getter
@Setter
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContratos;

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date alta;

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date vencimiento;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idControlador", nullable = false)
    private Controlador controlador;
}