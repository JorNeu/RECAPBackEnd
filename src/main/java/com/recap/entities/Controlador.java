package com.recap.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "controladores")
@Getter
@Setter
public class Controlador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idControlador;
    @NonNull
    private String nombre;
    @NonNull

    private String apellido;
    @NonNull

    private String dni;
    @NonNull

    private String numHabilitacion;

    @JsonManagedReference
    @OneToMany(mappedBy = "controlador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private List<Contrato> contratos;
}