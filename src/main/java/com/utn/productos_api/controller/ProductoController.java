package com.utn.productos_api.controller;

import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// Define como controlador REST
@RestController
// Define la ruta base para todos los endpoints
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    // Inyección de dependencias por constructor
    public ProductoController(ProductoService service) {
        this.service = service;
    }


    // Convierte Producto (Model) a ProductoResponseDTO
    private ProductoResponseDTO convertToDto(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setCategoria(producto.getCategoria());
        return dto;
    }

    // Convierte ProductoDTO a Producto (Model)
    private Producto convertToEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }


    // ====================================================================
    // ENDPOINTS CRUD

    /**
     * POST /api/productos -> Crear producto. Retorna 201 Created.
     */
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        // 1. Convertir DTO a Entidad
        Producto nuevoProducto = convertToEntity(productoDTO);

        // 2. Guardar en el servicio
        Producto productoGuardado = service.crearProducto(nuevoProducto);

        // 3. Convertir Entidad a ResponseDTO y retornar 201 CREATED
        ProductoResponseDTO responseDTO = convertToDto(productoGuardado);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED); // Código 201
    }

    /**
     * GET /api/productos -> Listar todos los productos. Retorna 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodos() {
        List<Producto> productos = service.obtenerTodos();

        // Convertir la lista de Productos a List<ProductoResponseDTO>
        List<ProductoResponseDTO> responseDTOs = productos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs); // Código 200
    }

    /**
     * GET /api/productos/{id} -> Obtener producto por ID. Retorna 200 OK o 404.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(this::convertToDto)
                // Si no se encuentra, la Parte 5 lanzará ProductoNotFoundException y se mapeará a 404
                .map(ResponseEntity::ok)
                // Temporalmente, si no existe retorna 404 (esto será mejorado en la Parte 5)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /api/productos/categoria/{categoria} -> Filtrar por categoría. Retorna 200 OK.
     */
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(@PathVariable Categoria categoria) {
        List<Producto> productos = service.obtenerPorCategoria(categoria);

        List<ProductoResponseDTO> responseDTOs = productos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs); // Código 200
    }

    /**
     * PUT /api/productos/{id} -> Actualizar producto completo. Retorna 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@PathVariable Long id,
                                                                  @Valid @RequestBody ProductoDTO productoDTO) {
        // 1. Convertir DTO a Entidad
        Producto productoAActualizar = convertToEntity(productoDTO);

        // 2. Actualizar en el servicio (el servicio maneja la validación de existencia)
        Producto productoActualizado = service.actualizarProducto(id, productoAActualizar);

        // 3. Convertir Entidad a ResponseDTO y retornar 200 OK
        ProductoResponseDTO responseDTO = convertToDto(productoActualizado);
        return ResponseEntity.ok(responseDTO); // Código 200
    }

    /**
     * PATCH /api/productos/{id}/stock -> Actualizar solo el stock. Retorna 200 OK.
     */
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(@PathVariable Long id,
                                                               @Valid @RequestBody ActualizarStockDTO stockDTO) {

        // 1. Actualizar stock en el servicio
        Producto productoActualizado = service.actualizarStock(id, stockDTO.getStock());

        // 2. Convertir Entidad a ResponseDTO y retornar 200 OK
        ProductoResponseDTO responseDTO = convertToDto(productoActualizado);
        return ResponseEntity.ok(responseDTO); // Código 200
    }

    /**
     * DELETE /api/productos/{id} -> Eliminar producto. Retorna 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        service.eliminarProducto(id);
        // Retorna 204 NO CONTENT
        return ResponseEntity.noContent().build();
    }
}