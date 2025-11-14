package com.utn.productos_api.service;

import com.utn.productos_api.model.Producto;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Anota con @Service
@Service
public class ProductoService {

    private final ProductoRepository repository;

    // Inyección de dependencias por constructor (buena práctica)
    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    // Método 1: crearProducto (guarda un nuevo producto)
    public Producto crearProducto(Producto producto) {
        return repository.save(producto);
    }

    // Método 2: obtenerTodos (retorna lista de todos los productos)
    public List<Producto> obtenerTodos() {
        return repository.findAll();
    }

    // Método 3: obtenerPorId (retorna Optional<Producto>)
    public Optional<Producto> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // Método 4: obtenerPorCategoria (usa el Query Method del repositorio)
    public List<Producto> obtenerPorCategoria(Categoria categoria) {
        return repository.findByCategoria(categoria);
    }

    // Método 5: actualizarProducto (actualiza el producto completo)
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        // Tip: Valida que el producto exista (se lanzará ProductoNotFoundException en Parte 5)
        Producto productoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado. Lanzar ProductoNotFoundException en Parte 5."));

        // Actualizar todos los campos
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());
        productoExistente.setCategoria(productoActualizado.getCategoria());

        return repository.save(productoExistente);
    }

    // Método 6: actualizarStock (actualiza solo el stock)
    public Producto actualizarStock(Long id, Integer nuevoStock) {
        // Valida que el producto exista
        Producto productoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado. Lanzar ProductoNotFoundException en Parte 5."));

        // El StockInsuficienteException opcional se puede implementar aquí,
        // pero la validación mínima se hace en el DTO (Parte 3).
        productoExistente.setStock(nuevoStock);
        return repository.save(productoExistente);
    }

    // Método 7: eliminarProducto (elimina producto por ID)
    public void eliminarProducto(Long id) {
        // Podrías validar si existe antes de eliminar, pero deleteById() ya maneja IDs inexistentes.
        repository.deleteById(id);
    }
}