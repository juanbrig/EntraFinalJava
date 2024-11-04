package com.juan.example.service;

import com.juan.example.entity.DetalleFactura;
import com.juan.example.entity.Factura;
import com.juan.example.entity.Product;
import com.juan.example.exception.InsufficientStockException;
import com.juan.example.exception.NotFoundException;
import com.juan.example.model.FacturaRequest;
import com.juan.example.model.LineaFactura;
import com.juan.example.repository.FacturaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Service



public class FacturaService {

    @Autowired

    private ProductService productoService;

    @Autowired

    private FacturaRepository facturaRepository;

    private static final Logger log = LoggerFactory.getLogger(FacturaService.class);
    public Factura crearNuevaFactura(Factura factura, FacturaRequest facturaRequest) {

        double total = 0;



        List<DetalleFactura> detallesFactura = new ArrayList<>();

        for (LineaFactura linea : facturaRequest.getLineas()) {

            Product producto = productoService.findById(linea.getProductoId()).orElseThrow(() -> new NotFoundException("Producto no encontrado"));

            if (producto.getCantidad() < linea.getCantidad()) {

                throw new InsufficientStockException("Stock insuficiente para el producto " + producto.getId());

            }

            DetalleFactura detalle = new DetalleFactura();

            detalle.setProducto(producto);

            detalle.setCantidad(linea.getCantidad());

            detalle.setPrecioUnitario(linea.getPrecioUnitario());

            detalle.setFactura(factura);

            detallesFactura.add(detalle);

            total += linea.getCantidad() * linea.getPrecioUnitario();

        }



        factura.setDetallesFactura(detallesFactura);

        factura.setTotal(total);

        factura.setFecha(new Date());

        return facturaRepository.save(factura);

    }

}