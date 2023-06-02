package com.example.tienda.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.tienda.model.Cajero;
import com.example.tienda.model.Producto;
import com.example.tienda.repository.CajeroRepository;
import com.example.tienda.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private CajeroRepository cajeroRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public Cajero saveCajero(String nombreEmpleado) {
        Cajero cajero = Cajero.builder().nombreEmpleado(nombreEmpleado).build();
        cajeroRepository.save(cajero);
        return cajero;
    }

    public Producto saveProducto(String nombre, Integer cantidad) {
        Producto producto = Producto.builder().nombre(nombre).cantidad(cantidad).build();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SaveProductoTask task = new SaveProductoTask(producto, productoRepository);
        forkJoinPool.invoke(task);

        return producto;
    }

    private static class SaveProductoTask extends RecursiveTask<Void> {
        private final Producto producto;
        private final ProductoRepository productoRepository;

        public SaveProductoTask(Producto producto, ProductoRepository productoRepository) {
            this.producto = producto;
            this.productoRepository = productoRepository;
        }

        @Override
        protected Void compute() {
            productoRepository.save(producto);
            return null;
        }
    }
}
