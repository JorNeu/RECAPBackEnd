package com.recap.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "establecimientos")
@Getter
@Setter
public class Establecimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstablecimiento;
    @NonNull
    private String nombre;
    @NonNull
    private Integer factorOcupacional;
    private String direccion;
    @NonNull
    private Boolean habilitacion;

    private String razonSocial;
    @JsonManagedReference
    @OneToMany(mappedBy = "establecimiento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ContratoEmpEst> contratoEmpEsts;
}