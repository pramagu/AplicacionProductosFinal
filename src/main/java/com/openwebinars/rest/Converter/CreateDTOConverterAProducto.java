package com.openwebinars.rest.Converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.openwebinars.rest.Model.Producto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateDTOConverterAProducto {
    private final ModelMapper modelMapper;

    public Producto convertToProducto(CreateProductoDTO productoDTO) {
        return modelMapper.map(productoDTO, Producto.class);
    }

}
