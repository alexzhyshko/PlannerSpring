package com.zhyshko.convert.dto2entity.context;

import java.util.IdentityHashMap;
import java.util.Map;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;


@Component
public class CycleAvoidingMappingContextSectionToEntity {
	private Map<com.zhyshko.dto.Section, com.zhyshko.model.Section> knownInstances = new IdentityHashMap<>();

    @BeforeMapping
    public com.zhyshko.model.Section getMappedInstance(com.zhyshko.dto.Section source, @TargetType Class<com.zhyshko.model.Section> targetType) {
        return knownInstances.get(source);
    }

    @BeforeMapping
    public void storeMappedInstance(com.zhyshko.dto.Section source, @MappingTarget com.zhyshko.model.Section target) {
        knownInstances.put( source, target );
    }
}