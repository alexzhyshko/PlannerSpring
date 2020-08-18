package com.zhyshko.convert.entity2dto.context;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
public class CycleAvoidingMappingContextUserToDto {
    private Map<com.zhyshko.model.User, com.zhyshko.dto.User> knownInstances = new IdentityHashMap<>();

    public com.zhyshko.dto.User getMappedInstance(com.zhyshko.model.User source, @TargetType Class<com.zhyshko.dto.User> targetType) {
        return knownInstances.get(source);
    }

    public void storeMappedInstance(com.zhyshko.model.User source, @MappingTarget com.zhyshko.dto.User target) {
        knownInstances.put( source, target );
    }
    
    public int getInstanceCount() {
    	return this.knownInstances.size();
    }
    
    public List<com.zhyshko.dto.User> getAllKnownInstances(){
    	return knownInstances.values().stream().collect(Collectors.toList());
    }
}