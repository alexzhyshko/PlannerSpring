package com.zhyshko.convert.entity2dto.context;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;


@Component
public class CycleAvoidingMappingContextNotificationToDto {
    private Map<com.zhyshko.model.Notification, com.zhyshko.dto.Notification> knownInstances = new IdentityHashMap<>();

    public com.zhyshko.dto.Notification getMappedInstance(com.zhyshko.model.Notification source, @TargetType Class<com.zhyshko.dto.Notification> targetType) {
        return knownInstances.get(source);
    }

    public void storeMappedInstance(com.zhyshko.model.Notification source, @MappingTarget com.zhyshko.dto.Notification target) {
        knownInstances.put( source, target );
    }
    
    public int getInstanceCount() {
    	return this.knownInstances.size();
    }
    
    public List<com.zhyshko.dto.Notification> getAllKnownInstances(){
    	return knownInstances.values().stream().collect(Collectors.toList());
    }
}