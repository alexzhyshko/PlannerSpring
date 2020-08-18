package com.zhyshko.convert.entity2dto.context;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;


@Component
public class CycleAvoidingMappingContextDashboardToDto {
    private Map<com.zhyshko.model.Dashboard, com.zhyshko.dto.Dashboard> knownInstances = new IdentityHashMap<>();

    public com.zhyshko.dto.Dashboard getMappedInstance(com.zhyshko.model.Dashboard source, @TargetType Class<com.zhyshko.dto.Dashboard> targetType) {
        return knownInstances.get(source);
    }

    public void storeMappedInstance(com.zhyshko.model.Dashboard source, @MappingTarget com.zhyshko.dto.Dashboard target) {
        knownInstances.put( source, target );
    }
    
    public int getInstanceCount() {
    	return this.knownInstances.size();
    }
    
    public List<com.zhyshko.dto.Dashboard> getAllKnownInstances(){
    	return knownInstances.values().stream().collect(Collectors.toList());
    }
}