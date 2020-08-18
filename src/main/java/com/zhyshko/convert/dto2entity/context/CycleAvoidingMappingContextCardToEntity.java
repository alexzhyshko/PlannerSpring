package com.zhyshko.convert.dto2entity.context;

import java.util.IdentityHashMap;
import java.util.Map;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;


@Component
public class CycleAvoidingMappingContextCardToEntity {
    private Map<com.zhyshko.dto.Card, com.zhyshko.model.Card> knownInstances = new IdentityHashMap<>();

    @BeforeMapping
    public com.zhyshko.model.Card getMappedInstance(com.zhyshko.dto.Card source, @TargetType Class<com.zhyshko.model.Card> targetType) {
        return knownInstances.get(source);
    }

    @BeforeMapping
    public void storeMappedInstance(com.zhyshko.dto.Card source, @MappingTarget com.zhyshko.model.Card target) {
        knownInstances.put( source, target );
    }
}