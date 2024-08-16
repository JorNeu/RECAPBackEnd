package com.recap.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ControladorDTO {
    private Long idControlador;
    private String nombre;
    private String apellido;
    private String dni;
    private String numHabilitacion;
}