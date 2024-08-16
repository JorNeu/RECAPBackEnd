package com.recap.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ContratoEmpEstDTO {
    private Long idContratoEmpEst;
    private Date alta;
    private Date vencimiento;
    private String observaciones;
    private Long idEmpresa;
    private Long idEstablecimiento;
}