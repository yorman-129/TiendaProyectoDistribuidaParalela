package com.example.tienda.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Producto {
    @Id
    private String productoId;
    private String nombre;
    private Integer cantidad;

}
