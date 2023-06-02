package com.example.tienda.repository;

import com.example.tienda.model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepository extends MongoRepository<Producto, String> {
    Producto findByProductoId(String productoId);
}
