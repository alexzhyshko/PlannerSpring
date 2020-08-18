package com.zhyshko.convert.dto2entity.context;

import java.util.IdentityHashMap;
import java.util.Map;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;


@Component
public class CycleAvoidingMappingContextNotificationToEntity {
	private Map<com.zhyshko.dto.Notification, com.zhyshko.model.Notification> knownInstances = new IdentityHashMap<>();

    @BeforeMapping
    public com.zhyshko.model.Notification getMappedInstance(com.zhyshko.dto.Notification source, @TargetType Class<com.zhyshko.model.Notification> targetType) {
        return knownInstances.get(source);
    }

    @BeforeMapping
    public void storeMappedInstance(com.zhyshko.dto.Notification source, @MappingTarget com.zhyshko.model.Notification target) {
        knownInstances.put( source, target );
    }
}