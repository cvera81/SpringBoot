package com.intercorp.Post.mangement.dto;

import lombok.Data;


import java.util.Date;

@Data
public class ClienteDTO {
    private String id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private Date  fechaNacimiento;
}
