package com.zhyshko.convert.dto2entity.context;

import java.util.IdentityHashMap;
import java.util.Map;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;


@Component
public class CycleAvoidingMappingContextDashboardToEntity {
	private Map<com.zhyshko.dto.Dashboard, com.zhyshko.model.Dashboard> knownInstances = new IdentityHashMap<>();

    @BeforeMapping
    public com.zhyshko.model.Dashboard getMappedInstance(com.zhyshko.dto.Dashboard source, @TargetType Class<com.zhyshko.model.Dashboard> targetType) {
        return knownInstances.get(source);
    }

    @BeforeMapping
    public void storeMappedInstance(com.zhyshko.dto.Dashboard source, @MappingTarget com.zhyshko.model.Dashboard target) {
        knownInstances.put( source, target );
    }
}