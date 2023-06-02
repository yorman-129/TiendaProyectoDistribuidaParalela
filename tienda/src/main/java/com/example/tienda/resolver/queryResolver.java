package com.example.tienda.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.tienda.model.Cajero;
import com.example.tienda.model.Producto;
import com.example.tienda.repository.CajeroRepository;
import com.example.tienda.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;



public class queryResolver  implements GraphQLQueryResolver {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CajeroRepository cajeroRepository;

    public Iterable<Cajero> findAllCajeros(){
        return cajeroRepository.findAll();
    }

    public Iterable<Producto> findAllProductos(){
        return productoRepository.findAll();
    }
}
