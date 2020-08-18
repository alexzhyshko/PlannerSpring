package com.zhyshko.convert.dto2entity.context;

import java.util.IdentityHashMap;
import java.util.Map;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
public class CycleAvoidingMappingContextUserToEntity {
	private Map<com.zhyshko.dto.User, com.zhyshko.model.User> knownInstances = new IdentityHashMap<>();

    @BeforeMapping
    public com.zhyshko.model.User getMappedInstance(com.zhyshko.dto.User source, @TargetType Class<com.zhyshko.model.User> targetType) {
        return knownInstances.get(source);
    }

    @BeforeMapping
    public void storeMappedInstance(com.zhyshko.dto.User source, @MappingTarget com.zhyshko.model.User target) {
        knownInstances.put( source, target );
    }
}