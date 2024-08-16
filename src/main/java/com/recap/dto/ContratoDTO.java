package com.recap.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ContratoDTO {
    private Long idContratos;
    private Date alta;
    private Date vencimiento;
    private Long idEmpresa;
    private Long idControlador;
}