package com.recap.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstablecimientoDTO {
    private Long idEstablecimiento;
    private String nombre;
    private Integer factorOcupacional;
    private String direccion;
    private Boolean habilitacion;
    private String razonSocial;
}