package com.juan.example.model;

import lombok.Data;



import java.util.List;



@Data

public class FacturaRequest {

    private Cliente cliente;

    private List<LineaFactura> lineas;

}