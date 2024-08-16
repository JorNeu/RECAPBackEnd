package com.recap.config;

import com.recap.dto.ControladorDTO;
import com.recap.entities.Controlador;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Configurar para ignorar el campo idControlador durante el mapeo
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<ControladorDTO, Controlador>() {
            @Override
            protected void configure() {
                skip(destination.getIdControlador());
            }
        });
        return modelMapper;
    }
}