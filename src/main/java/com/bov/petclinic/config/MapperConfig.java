package com.bov.petclinic.config;

import com.bov.petclinic.mapping.PetDtoMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;


@AllArgsConstructor
@org.springframework.context.annotation.Configuration
public class MapperConfig {
    private PetDtoMapper petDtoMapper;

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
                .getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        addConventers(modelMapper);
        return modelMapper;
    }
    private void addConventers(ModelMapper modelMapper){
        modelMapper.addConverter(petDtoMapper);
    }
}
