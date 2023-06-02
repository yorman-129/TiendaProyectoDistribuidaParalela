package com.example.tienda.repository;

import com.example.tienda.model.Cajero;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajeroRepository extends MongoRepository<Cajero, String> {
    Cajero findByCajeroId(String cajeroId);
}
